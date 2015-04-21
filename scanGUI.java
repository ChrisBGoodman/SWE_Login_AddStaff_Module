/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.JTextArea;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class scanGUI extends JFrame implements Runnable, ThreadFactory {

	private static final long serialVersionUID = 6441489157408381878L;

	private Executor executor = Executors.newSingleThreadExecutor(this);

	private Webcam webcam = null;
	private WebcamPanel panel = null;
	private JTextArea textarea = null;
        
                
        private JTable studentTable;
        
        private JPanel mainPanel;
        private JPanel studentPanel;
        private JPanel lowerPanel;
        private JLabel logoLabel;
        
        private JScrollPane scrollPane;
        
        private ImageIcon logo;
        
        private staffCourseController scc;
        
        private ArrayList<Student> stu;
        
        private String seq;
        private String cCode;
        
        private JButton endClass;
        

	scanGUI(String courseCode, String courseSeq, ArrayList<Student> students) 
        {
		super();

		setLayout(new BorderLayout());
		setTitle("Read Bar Code With Webcam");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Dimension size = WebcamResolution.QVGA.getSize();

		webcam = Webcam.getWebcams().get(0);
		webcam.setViewSize(size);

                
                
		panel = new WebcamPanel(webcam);
		panel.setPreferredSize(new Dimension(600,600));

		textarea = new JTextArea();
		textarea.setEditable(false);
		textarea.setPreferredSize(new Dimension(200,200));
                textarea.setFont(new Font("Serif", Font.BOLD, 15));

                
                                
                endClass = new JButton("End Class");
                endClass.addActionListener(new ActionListener()     //Action for submit button
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        webcam.close();
                        dispose();
                       
                    }
                });

                
                
                //put the e-attendace image up
                logo = new ImageIcon(getClass().getResource("/images/eattdancelogo.jpg")); //E-attendance banner 
                logoLabel = new JLabel(logo);
                
                // -- Panel Settings --
                mainPanel = new JPanel();
                mainPanel.setLayout(new BorderLayout());
                mainPanel.setPreferredSize(new Dimension(640,440));
		
                studentPanel = new JPanel();
                studentPanel.setPreferredSize(new Dimension(600, 400));
                
                lowerPanel = new JPanel();
                lowerPanel.setLayout(new BorderLayout());
                lowerPanel.add(textarea, BorderLayout.WEST);
                lowerPanel.add(endClass, BorderLayout.EAST);
               
                

        
                studentPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(10, 10, 10, 10);
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 1;
                c.weighty = 1;
                
                // -- Make Table of Students --
                studentTable = new JTable(100, 4);
                studentTable.setEnabled(false);
                writeStudentTable(students);
                
                
                // -- Add table to Scrolling Pane --                
                scrollPane = new JScrollPane(studentTable);
                                
                studentPanel.add(scrollPane, c);

                mainPanel.add(studentPanel);
                
		add(panel, BorderLayout.WEST);
		add(mainPanel);
                

                add(logoLabel, BorderLayout.NORTH);
                add(lowerPanel, BorderLayout.SOUTH);
                
		pack();
		setVisible(true);
                
                
                //Holding values needed for other functions
                stu = new ArrayList<Student>(); //stu is list of student's in course
                stu = students;                 //
                seq = courseSeq;                //seq holds the unique seq number of the course
                cCode = courseCode;
                
		executor.execute(this);
	}

	@Override
	public void run() {
            
            scc = new staffCourseController();

		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Result result = null;
			BufferedImage image = null;

			if (webcam.isOpen()) 
                        {
                                // -- Nothing found in image, continue
				if ((image = webcam.getImage()) == null) 
                                {
					continue;
				}

                                // -- Try to get the CODE and read its bitmap -- 
                                LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

				try {
					result = new MultiFormatReader().decode(bitmap);
                                        
				} catch (NotFoundException e) {
					// -- fall thru, it means there is no Barcode in image --
				}
			}

                        // -- Found an ID --
			if (result != null) 
                        {
                            textarea.setText("ID Found: " + result.getText());

                            // -- Find location of scanned student in the list
                            Student s = new Student();
                            int locationScanned = s.findIDInList(result.getText(), stu);
                            
                            if (locationScanned == -1) // Id not found in list, reRun
                                run();
                            
                            // -- Get the current time  --
                            Calendar rightNow = Calendar.getInstance();
                            int hour = rightNow.get(Calendar.HOUR_OF_DAY);
                            int min  = rightNow.get(Calendar.MINUTE);
                            
                            
                            // -- Update Table with student scan time --
                            stu.get(locationScanned).setTimeInHour(hour);
                            stu.get(locationScanned).setTimeInMin(min);
                            
                            try
                            {
                                // -- Check Status and update student on Table -- 
                                String status = scc.checkStatus(seq, Integer.toString(hour), Integer.toString(min));
                                stu.get(locationScanned).setStatus(status);
                                writeStudentTable(stu); 
                                
                                // -- Write to the Database --
                                scc.studentScanIn(stu.get(locationScanned).getID(), cCode, seq, hour, min);
                            } catch (SQLException ex)
                            {
                                Logger.getLogger(scanGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                                           

                            // -- Call the splash screen --
                            scanGUISplash splash = new scanGUISplash(4000);
			}

		} while (true);
	}

	@Override
	public Thread newThread(Runnable r) 
        {
		Thread t = new Thread(r, "example-runner");
		t.setDaemon(true);
		return t;
	}
        
        public final void writeStudentTable(ArrayList<Student> courseStudents)
        {
            studentTable.setValueAt("                    NAME"      , 0, 0);
            studentTable.setValueAt("                  STUDENT ID", 0, 1);
            studentTable.setValueAt("                    TIME IN"   , 0, 2);
            studentTable.setValueAt("                    STATUS"    , 0, 3);
            int y = 0;
            for (int x = 1; x <= courseStudents.size(); x++)
                {
                    y = 0;
                    studentTable.setValueAt(courseStudents.get(x-1).getFName() + " " + courseStudents.get(x-1).getLName(), x, y);
                    y++;
                    studentTable.setValueAt(courseStudents.get(x-1).getID(), x, y);
                    y++;
                    studentTable.setValueAt(courseStudents.get(x-1).getTimeInHour() + ":" +
                            courseStudents.get(x-1).getTimeInMin(), x, y); 
                    y++;
                    studentTable.setValueAt(courseStudents.get(x-1).getStatus(), x, y);
                }
        }  
}