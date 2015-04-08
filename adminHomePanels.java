/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 *
 * @author ChrisGoodman
 */
public class adminHomePanels extends JFrame
{
    
    private ImageIcon image1;
	
        private JLabel label1;
	
        private JPanel panel;
	private JTable table;
        private JPanel addStaffPanel;
	
        private JFrame frame;
	protected JTabbedPane tabPane;
        
        private testPanel p;
        
    adminHomePanels()
    {
     super("Add Staff GUI");
		this.setSize(700, 850);
		setLayout(new FlowLayout());
                
                       
		//put the e-attendace image up
		image1 = new ImageIcon(getClass().getResource("images/eattdancelogo.jpg")); //E-attendance banner 
		label1 = new JLabel(image1);
		add(label1);
	
                // -- Panel for Admin Add staff View
                addStaffPanel = new JPanel();
                addStaffPanel.setLayout(new BorderLayout());
                addStaffPanel.setPreferredSize(new Dimension(640,440));
		
		JPanel panel4 = new JPanel();
                
                // -- A Test Panel --
                p = new testPanel();
                
                // -- Panel for admin add course view --
                // -- As of now all of the below is required to gather the data from a hardwired excel source
                // -- and then used to construct the GUI and add it to the tab
                add_staff_GUI asg = new add_staff_GUI();
                adminCourseController acc = new adminCourseController();
       
                readFromExcel excelReader = new readFromExcel();
                excelReader = new readFromExcel();
                
                ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
                list = excelReader.getData();
                
                adminCourseGUI acg = new adminCourseGUI(list);

                
                
                // -- Makes the tabs --
		JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Courses", acg);
                tabbedPane.addTab("Staff", asg);
                tabbedPane.addTab("Students", p);
                tabbedPane.addTab("Logout", panel4);
                add(tabbedPane);
                
                
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
    }
}

