package SWE_Login_AddStaff_Module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * @author Christian Goodman
 * @since 03/16/2015
 */

class LoginGUI extends JFrame
{
    private String userName;
    private String passWord;
    
    private ImageIcon lockImage;
    
    private JLabel iconLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel infoLabel;
    
    private JPanel loginPanel;
    private JPanel mainPanel;
    private JPanel infoPanel;
    
    private JTextField usernameJTF;
    
    private LoginController lc;
    
    LoginGUI()  //constructor called when wanting to load on loginGUI
    {
        //JFrame options
        super("Login");                             
        this.setSize(600, 450);	
        //setLayout(new BorderLayout());
        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS); // top to bottom
        setLayout(boxLayout);
        
        //Login Fields optiions
        JLabel usernameLabel    = new JLabel("Username");   
        JLabel passwordLabel    = new JLabel("Password");
        JTextField usernameJTF  = new JTextField("", 15);
        JPasswordField passwordJTF  = new JPasswordField("", 15);
        usernameJTF.setColumns(15);
        passwordJTF.setColumns(15);
        
        // -- BUTTONS --
        JButton submitJB = new JButton("Submit");
        JButton cancelJB = new JButton("Cancel");
        submitJB.setActionCommand("Submit Button");
        submitJB.addActionListener(new ActionListener()     //Action for submit button
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {                               
                System.out.println("Login Submit Button pressed");                            
                userName = usernameJTF.getText();   
                passWord = passwordJTF.getText();
                lc = new LoginController(userName, passWord);   //Try to login with text from the JTF's
                int user_type = lc.validateUser();
                int admin = 1;
                int staff = 2;
                
                if (lc.loginSuccess == true && user_type == admin)//If succesfull close login GUI 
                {                                               //and open up Admin View 
                    dispose();
                    //add_staff_GUI staffGUI = new add_staff_GUI();
                    adminHomePanels ahp = new adminHomePanels();
                }
            }
        });
        
        cancelJB.addActionListener(new ActionListener()     //Action for submit button
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {                               
                System.out.println("Login cancel Button pressed");                            
                System.exit(0);
            }
        });
        
       

         //Login Panel options
        loginPanel = new JPanel();  
        loginPanel.setMaximumSize(new Dimension(1280,100));
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        loginPanel.setLayout(new GridLayout(3,2));
        
        //Info Panel & Label Options
        infoLabel = new JLabel("E-Attedance System - Developed by Paul Young's Software Engineering students.");
        infoPanel = new JPanel();
        infoPanel.setMaximumSize(new Dimension(1280,100));
        
        
        //Icon options
        lockImage = new ImageIcon(getClass().getResource("images/lock.jpg"));   
        iconLabel = new JLabel(lockImage);
        add(iconLabel, BorderLayout.NORTH);
        
   
        //Add Panels to Frame    
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameJTF);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordJTF);
        loginPanel.add(submitJB);
        loginPanel.add(cancelJB);
        
        JLabel test = new JLabel("Developed 2015. For support email test@uca.edu");
        
        infoPanel.add(infoLabel);
        infoPanel.add(test);
                
        add(iconLabel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.SOUTH);
        add(loginPanel, BoxLayout.Y_AXIS);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        		
        this.setVisible(true);

    } //End Constructor
} //End Class

