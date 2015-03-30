


package SWE_Login_AddStaff_Module;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import static java.util.Collections.list;

/**
 * @author James, Christian
 *
 */
public class add_staff_GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel staff_GUI;
	private ImageIcon image1;
	private JLabel label1;
	private JPanel panel;
	private JTable table;
	private JFrame frame;
	protected JTabbedPane tabPane;

        private ArrayList<String> list; //Will hold a list of all Staff Members. Values will be returned as a string
       
	protected JComponent makeTextPanel(String text) 
        {
            JPanel panel = new JPanel(false);
            JLabel filler = new JLabel(text);
            panel.setLayout(new GridLayout(1, 1));
            panel.add(filler);
            return panel;
	}
        
        add_staff_GUI() 
            {
              
                super("Add Staff GUI");
		this.setSize(700, 850);
		setLayout(new FlowLayout());
                       
		//put the e-attendace image up
		//image1 = new ImageIcon(getClass().getResource("banner1.jpg")); //E-attendance banner 
		label1 = new JLabel(image1);
		add(label1);
	
                //ImageIcon icon = new ImageIcon(getClass().getResource("name.PNG"));

                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BorderLayout());
                mainPanel.setPreferredSize(new Dimension(640,440));
		
                JPanel staffPanel = new JPanel();
                staffPanel.setPreferredSize(new Dimension(600, 400));

		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
                                                               //Makes the tabbs
		JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Courses", panel2);
                tabbedPane.addTab("Staff", mainPanel);
                tabbedPane.addTab("Students", panel3);
                tabbedPane.addTab("Logout", panel4);
                add(tabbedPane);
        
                staffPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(10, 10, 10, 10);
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 1;
                c.weighty = 1;
                JTable staffTable = new JTable(10, 3);
        
                list = new ArrayList<String>();             //Add items to staff Table
                list.add("Item1");
                list.add("Item 2");
                list.add(("Item 3"));
                staffTable.setValueAt(list.get(0), 0, 0); //Just an example. Use a nested for loop looping 
                staffTable.setValueAt(list.get(1), 0, 1); //thru list.size() to add each item in the list.
                staffTable.setValueAt(list.get(2), 0, 2); //setValueAt(value, x Coord, Y Coord);
        /*
        Real code will look something like 
        for (int x = 0; x < list.size(); x++)
        {    
            for (int y = 0; y < 3; y++)
            {
                table.setValueAt(list.get(x).username.toString(), x, y);
                table.setValueAt(list.get(x).postion.toString(), x, y);
                table.setValueAt(list.get(x).email.toString(), x, y);
            }   
        }
        */
                staffPanel.add(staffTable, c);
        
                JButton Staff_Button = new JButton("Add Staff");
                JButton upload_Staff_Button = new JButton("Upload Staff");
        
                mainPanel.add(staffPanel, BorderLayout.NORTH);
                mainPanel.add(upload_Staff_Button, BorderLayout.LINE_START);
                mainPanel.add(Staff_Button, BorderLayout.LINE_END);
        
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
            } //End Constructor

	public static void main(String[] args) 
        {
		add_staff_GUI add_staff_GUI = new add_staff_GUI();
	}
} //End class
