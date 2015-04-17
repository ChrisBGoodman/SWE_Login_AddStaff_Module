/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 *
 * @author ChrisGoodman
 */
public class studentGUI extends JPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon image1;
	
        private JLabel label1;
	
        private JPanel panel;
	private JTable table;
        private JPanel mainPanel;
        private JPanel studentPanel;
	
        private JFrame frame;
	protected JTabbedPane tabPane;
        
        private JTable studentTable;
        private JScrollPane scrollPane; 
        
        private JButton attendanceJB;
        private JButton logoutJB;
        
        private staffCourseController scc;

        

        private ArrayList<Student> allStudents; 
        private ArrayList<Student> courseStudents;
        private ArrayList<String>  IDList;
        
       
	
        
        studentGUI(String courseCode) throws SQLException 
            {
		this.setSize(500, 550);
		setLayout(new FlowLayout());
                
                
                //- Panel to hold all add Staff interface --
                mainPanel = new JPanel();
                mainPanel.setLayout(new BorderLayout());
                mainPanel.setPreferredSize(new Dimension(640,440));
		
                studentPanel = new JPanel();
                studentPanel.setPreferredSize(new Dimension(600, 400));

        
                studentPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(10, 10, 10, 10);
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 1;
                c.weighty = 1;
                studentTable = new JTable(100, 3);
                studentTable.setEnabled(false);
        
                // -- Get all students in the Database --
                scc = new staffCourseController();
                allStudents = new ArrayList<Student>();             
                allStudents = scc.getStudentList();
                
                // -- Find all students in a course by code --
                IDList = scc.getCourseStudentsID(courseCode);
                courseStudents = new ArrayList<Student>();
                
                // -- Add students found into a new list to be displayed --
                for (int i = 0; i < IDList.size(); i++)
                {
                    for (int x = 0; x < allStudents.size(); x++)
                    {
                        if (allStudents.get(x).getID().toString().equals(IDList.get(i).toString()))
                        {
                            String f, l, id, e, url;
                            f = allStudents.get(x).getFName();
                            l = allStudents.get(x).getLName();
                            id = allStudents.get(x).getID();
                            e = allStudents.get(x).getEmail();
                            url = allStudents.get(x).getUrl();
                            courseStudents.add(new Student(f,l,id,e,url));
                        }
                    }
                }

                //Gathers data from the ArrayList of students and inserts it into the JTable
                writeStudentTable();
        
                
                // -- Adding Scrolling Table -- 
                scrollPane = new JScrollPane(studentTable);
                studentPanel.add(scrollPane, c);
        
                // -- Add Buttons & Listeners --
                attendanceJB = new JButton("Take Attendance");
                attendanceJB.setActionCommand("take attendance" + courseCode);
                attendanceJB.addActionListener(new ActionListener()     //Action for submit button
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        String cmd = e.getActionCommand();
                        if (cmd.equals("take attendance" + courseCode))
                        {
                            System.out.println("take attendance clicked" + courseCode);
                            staffCourseController scc = new staffCourseController();
                            scc.loadScanGUI(courseCode);
                        }
                    }
                });
               
                // -- Add to Staff Panel --
                mainPanel.add(studentPanel, BorderLayout.NORTH);
                mainPanel.add(attendanceJB, BorderLayout.LINE_END);
                
                add(mainPanel);
               
		this.setVisible(true);
            } //End Constructor
        
        
        public final void writeStudentTable()
        {
            studentTable.setValueAt("                    NAME", 0, 0);
            studentTable.setValueAt("                    STUDENT ID", 0, 1);
            studentTable.setValueAt("                    TIME IN", 0, 2);
            int y = 0;
            for (int x = 1; x <= courseStudents.size(); x++)
                {
                    y = 0;
                    studentTable.setValueAt(courseStudents.get(x-1).getFName() + " " + courseStudents.get(x-1).getLName(), x, y);
                    y++;
                    studentTable.setValueAt(courseStudents.get(x-1).getID(), x, y);
                    y++;
                    studentTable.setValueAt(courseStudents.get(x-1).getTimeIn(), x, y); 
                }
        }      
} //End class


