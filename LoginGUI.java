package SWE_Login_AddStaff_Module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private JPanel loginPanel;
    private JPanel mainPanel;
    
    private JTextField usernameJTF;
    private JTextField passwordJTF;
    
    private LoginController lc;
    
    LoginGUI()  //constructor called when wanting to load on loginGUI
    {
        //JFrame options
        super("Login");                             
        this.setSize(700, 850);	
        setLayout(new BorderLayout());
        
        //Login Fields optiions
        JLabel usernameLabel    = new JLabel("Username");   
        JLabel passwordLabel    = new JLabel("Password");
        JTextField usernameJTF  = new JTextField("", 15);
        JTextField passwordJTF  = new JTextField("", 15);
        usernameJTF.setColumns(15);
        passwordJTF.setColumns(15);
        
        JButton submitJB = new JButton("Submit");
        submitJB.setActionCommand("Submit Button");
        submitJB.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {                               
                System.out.println("Login Submit Button pressed");                            
                userName = usernameJTF.getText();   
                passWord = passwordJTF.getText();
                lc = new LoginController(userName, passWord);   //Try to login with text from the JTF's
                if (lc.loginSuccess == true)                    //If succesfull close login GUI 
                {                                               //and open up Admin View 
                    dispose();
                    add_staff_GUI staffGUI = new add_staff_GUI();
                }
            }
        });

         //Login Panel options
        loginPanel = new JPanel();  
        loginPanel.setMaximumSize(new Dimension(100,300));
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        
        //Icon options
        lockImage = new ImageIcon(getClass().getResource("images/lock.jpg"));   
        iconLabel = new JLabel(lockImage);
        add(iconLabel, BorderLayout.NORTH);
        
   
        //Add Panels to Frame    
        add(iconLabel, BorderLayout.NORTH);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameJTF);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordJTF);
        loginPanel.add(submitJB);
        add(loginPanel, BorderLayout.CENTER);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        		
        this.setVisible(true);

    } //End Constructor
} //End Class

