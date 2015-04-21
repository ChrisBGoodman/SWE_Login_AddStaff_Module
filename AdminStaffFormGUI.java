package SWE_Login_AddStaff_Module;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.E;
import static java.lang.StrictMath.E;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

/**
 * @author ChrisGoodman, James Johnson, Dennis Smith, Ben Boaz, Sherry Wang
 * @since 03/16/2015
 */

public class AdminStaffFormGUI extends JFrame
{
    //vars
    private ImageIcon image1;
    private JLabel label1;
    private JLabel usernameJL;
    private JLabel idJL;
    private JLabel nameJL;
    private JLabel passwordJL;
    private JLabel positionJL;
    private JLabel courseListLabel;
    private JLabel emailJL;
    
    private JTextField usernameJTF;
    private JTextField passwordJTF;
    private JTextField nameJTF;
    private JTextField positionJTF;
    private JTextField idJTF;
    private JTextField emailJTF;
    
    private String username;
    private String password;
    private String id;
    private String name; 
    private String position;
    private String email;
    private String[] courses;
   
    
    private JList courseList;
    private JScrollPane listScrollPane;
    
    private JButton submit;
    private JButton cancel;
    
    private JPanel mainPanel;
    private JPanel imagePanel;
    private JPanel formPanel;
    private JPanel coursePanel;
    private JPanel buttonPanel;
    
    private AdminStaffController asc;
    
    AdminStaffFormGUI()
    { 
        super("Add Staff Form GUI");
        this.setSize(500, 850);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setLayout(new FlowLayout());
        
        // -- Initialize Panels --
        mainPanel   = new JPanel();
        imagePanel  = new JPanel();
        formPanel   = new JPanel();
        coursePanel = new JPanel();
        buttonPanel = new JPanel();
        
        // -- Set Panel Layouts --
        formPanel.setLayout(new GridLayout(6,2));
        coursePanel.setLayout(new GridLayout(3,2));
        buttonPanel.setLayout(new GridLayout(1,2));
        mainPanel.setLayout(new BorderLayout());
                
        
        // -- put the image up --
        image1 = new ImageIcon(getClass().getResource("/images/staffBlackboard.jpg")); //E-attendance banner 
        label1 = new JLabel(image1);
        imagePanel.add(label1);
        
        // -- ID -- 
        idJL    = new JLabel("Staff ID: (UNIQUE)");
        idJTF = new JTextField();
        idJTF.setColumns(16);
        formPanel.add(idJL);
        formPanel.add(idJTF);
        
        // -- NAME --
        nameJL    = new JLabel("Staff Name: ");
        nameJTF = new JTextField();
        nameJTF.setColumns(16);
        formPanel.add(nameJL);
        formPanel.add(nameJTF);
        
        // -- POSITION -- 
        positionJL    = new JLabel("Position: ");
        positionJTF = new JTextField();
        positionJTF.setColumns(16);
        formPanel.add(positionJL);
        formPanel.add(positionJTF);
        
        // -- EMAIL --
        emailJL  = new JLabel("Email: ");
        emailJTF = new JTextField();
        emailJTF.setColumns(16);
        formPanel.add(emailJL);
        formPanel.add(emailJTF);
        
        // -- USERNAME --
        usernameJL    = new JLabel("Username: ");
        usernameJTF = new JTextField();
        usernameJTF.setColumns(16);
        formPanel.add(usernameJL);
        formPanel.add(usernameJTF);
        
        // -- PASSWORD --
        passwordJL    = new JLabel("Password: ");
        passwordJTF = new JTextField();
        passwordJTF.setColumns(16);
        formPanel.add(passwordJL);
        formPanel.add(passwordJTF);
        
        
        // -- COURSES LIST --
        //Will be replaced with ArrayList of courses
        //ArrayList<course> courses = new ArrayList<course();
        //Then the list will be generated from the array of courses by name.
        ArrayList<ArrayList<String>> courses = new ArrayList<ArrayList<String>>();
        adminCourseController acc = new adminCourseController();
        courses = acc.runReturn();
        
        String[] c = new String[100];
        for (int x = 0; x < courses.size(); x++)
        {
            c[x] = courses.get(x).toString();
        }
        
        
        String sampleCourseData[] = {"apple", "orange", "banana", "pear", "pumpkin", "bears","lions", "tigers", "oMY!"};
        courseListLabel = new JLabel("Courses Taught: ");
        
        courseList      = new JList(c);
        listScrollPane = new JScrollPane(courseList);
        coursePanel.add(courseListLabel);
        coursePanel.add(listScrollPane);
        
        
        // -- BUTTONS -- 
        submit = new JButton("Submit");
        cancel  = new JButton("Cancel");
        coursePanel.add(submit);
        coursePanel.add(cancel);
        
        // -- ADD ALL PANELS --
        mainPanel.add(imagePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(coursePanel, BorderLayout.PAGE_END);
       // mainPanel.add(buttonPanel);
        add(mainPanel);
        
        
        // -- Actions Performed --
        submit.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {                               
                System.out.println("Submit Form Button pressed");   
                asc = new AdminStaffController();
                name     = nameJTF.getText();                
                id       = idJTF.getText();
                position = positionJTF.getText();
                email    = emailJTF.getText();
                username = usernameJTF.getText();   
                password = passwordJTF.getText();
                
                
               Object[] c = new Object[10]; //Assuming a staff could teach no more then 10 classes
               c = courseList.getSelectedValuesList().toArray(); //c holds list of selected courses
               // -- NEEDS TO BE FIXED, WAITING FOR COURSES RETURN TO BE FIXED --
                System.out.println(id + name + position + email + username + password);
                
                try
                {
                    asc.uploadStaffData(id, name, position, email, username, password, "2");
                } catch (SQLException ex)
                {
                    Logger.getLogger(AdminStaffFormGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                                   
                JOptionPane.showMessageDialog(null, "Submit Succesful!");
                asc.loadStaffGUI();
                dispose();
            }
        });           
        
        cancel.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {                               
                System.out.println("Cancel Form Button pressed");   
                dispose();
            }
        });         
        this.setVisible(true);
    }
   
}

