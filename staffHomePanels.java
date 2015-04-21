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
 * @author Christian Bryce Goodman
 */
public class staffHomePanels extends JFrame
{
    private ImageIcon image1;
	
    private JLabel label1;
	
    private JPanel panel;
    private JPanel addStaffPanel;

    private JTable table;
    
    protected JTabbedPane tabPane;
               
    private String[] staffCoursesTaught;
    private String[] staffCoursesTaughtSeq;
    private String   startTime;

        
    staffHomePanels(String username) throws SQLException
    {
        
        super("Add Staff GUI");
        this.setSize(700, 850);
	setLayout(new FlowLayout());
                
                       
        //put the e-attendace image up
	image1 = new ImageIcon(getClass().getResource("/images/eattdancelogo.jpg")); //E-attendance banner 
        label1 = new JLabel(image1);
	add(label1);
	
               
        // -- Panels Four each course
        JPanel course1 = new JPanel();
        JPanel course2 = new JPanel();
        JPanel course3 = new JPanel();
        JPanel course4 = new JPanel();
                
        
        // -- Initialize Panels to be used --        
        add_staff_GUI asg = new add_staff_GUI();
        adminCourseController acc = new adminCourseController();
        logoutGUI logout = new logoutGUI();
        
        // -- Initialize Staff Controller and get staff courses --
        staffCourseController scc = new staffCourseController();
        staffCoursesTaught    = scc.getStaffCoursesTaught(username);
        staffCoursesTaughtSeq = scc.getStaffCoursesTaughtSEQ(username);

          
        // -- Makes the tabs --
	JTabbedPane tabbedPane = new JTabbedPane();
        
        // -- ADD COURSES --
        // -- Dynamically create GUI's for each course with the studentGUI --
        // -- Tabs are displayed as "Course Code - Start Hour"
        
        if(staffCoursesTaught[0] != null)
        {   
            studentGUI sg = new studentGUI(staffCoursesTaught[0], staffCoursesTaughtSeq[0]);
            startTime = scc.getStaffCoursesTaughtTimeHour(staffCoursesTaughtSeq[0]);
            tabbedPane.addTab(staffCoursesTaught[0] + "-" + startTime, sg);
        }
        
        if(staffCoursesTaught[1] != null)
        {   
            studentGUI sg = new studentGUI(staffCoursesTaught[1], staffCoursesTaughtSeq[1]);            
            startTime = scc.getStaffCoursesTaughtTimeHour(staffCoursesTaughtSeq[1]);
            tabbedPane.addTab(staffCoursesTaught[1] + "-" + startTime, sg);
        }
        
        if(staffCoursesTaught[2] != null)
        {   
            studentGUI sg = new studentGUI(staffCoursesTaught[2], staffCoursesTaughtSeq[2]);
            startTime = scc.getStaffCoursesTaughtTimeHour(staffCoursesTaughtSeq[2]);
            tabbedPane.addTab(staffCoursesTaught[2] + "-" + startTime, sg);
        }
        
        if(staffCoursesTaught[3] != null)
        {   
            studentGUI sg = new studentGUI(staffCoursesTaught[3], staffCoursesTaughtSeq[3]);
            startTime = scc.getStaffCoursesTaughtTimeHour(staffCoursesTaughtSeq[3]);
            tabbedPane.addTab(staffCoursesTaught[3] + "-" + startTime, sg);
        }
        
        if(staffCoursesTaught[4] != null)
        {   
            studentGUI sg = new studentGUI(staffCoursesTaught[4], staffCoursesTaughtSeq[4]);
            startTime = scc.getStaffCoursesTaughtTimeHour(staffCoursesTaughtSeq[4]);
            tabbedPane.addTab(staffCoursesTaught[4] + "-" + startTime, sg);
        }
             
        // -- Adding logout pane --
        tabbedPane.addTab("Logout", logout);

        add(tabbedPane);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    
}

