/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 *
 * @author ChrisGoodman
 */
public class staffHomePanels extends JFrame
{
    
    private ImageIcon image1;
	
        private JLabel label1;
	
        private JPanel panel;
	private JTable table;
        private JPanel addStaffPanel;
	
        private JFrame frame;
	protected JTabbedPane tabPane;
        
        private testPanel p;
        
        
        private String[] staffCoursesTaught;
        
    staffHomePanels(String username) throws SQLException
    {
        
        super("Add Staff GUI");
        this.setSize(700, 850);
	setLayout(new FlowLayout());
                
                       
        //put the e-attendace image up
	image1 = new ImageIcon(getClass().getResource("/images/eattdancelogo.jpg")); //E-attendance banner 
        label1 = new JLabel(image1);
	add(label1);
	
               
        // -- A Test Panel --
        p = new testPanel();
        
        // -- Panels Four each course
        JPanel course1 = new JPanel();
        JPanel course2 = new JPanel();
        JPanel course3 = new JPanel();
        JPanel course4 = new JPanel();
                
        
        
               
                
        add_staff_GUI asg = new add_staff_GUI();
        adminCourseController acc = new adminCourseController();
        
        
        
        
        staffCourseController scc = new staffCourseController();
        scc.setUsername(username);
        staffCoursesTaught = scc.getStaffCoursesTaught(username);
          
        // -- Makes the tabs --
	JTabbedPane tabbedPane = new JTabbedPane();
        
        if(!staffCoursesTaught[0].isEmpty())
        {   
            studentGUI sg = new studentGUI(staffCoursesTaught[0]);
            tabbedPane.addTab(staffCoursesTaught[0], sg);
        }
        
        if(!staffCoursesTaught[1].isEmpty())
        {   
            studentGUI sg = new studentGUI(staffCoursesTaught[1]);
            tabbedPane.addTab(staffCoursesTaught[1], sg);
        }
        
        
        
        
        
        
        
        
       
        tabbedPane.addTab("Staff course 4", p);
        add(tabbedPane);

                
                
               
                
                
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    
    
    
    
    
}

