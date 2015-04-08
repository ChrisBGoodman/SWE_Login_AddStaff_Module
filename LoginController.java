package SWE_Login_AddStaff_Module;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author ChrisGoodman, James Johnson, Dennis Smith, Ben Boaz, Sherry Wang
 * @since 03/16/2015
 */

class LoginController
{
    protected boolean loginSuccess;
    private String host = "localhost";
    private String database = "eattendance";
    private String user = "cgoodman";
    private String pass = "swe2015";
    String username;
    String password;
    int userType;               //Integer value used to represent if a user is Admin(1) or Staff(2) or invalid (0)
    
    /**
     * 
     * @param u username passed from the LoginGUI
     * @param p password passed from the LoginGUI
     */
    LoginController(String u, String p)
    {
        username = u;
        password = p;
        login();
    }
    
    LoginController()
    {
        //Empty Constructor
    }
    
    
    /****************************************************************************
     * 
     */
    private boolean login()
    {
        String query, DBusername, DBpassword;
        
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://" + host
                    + "/" + database + "?"
                    + "user=" + user
                    + "&password=" + pass);
        
            st = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
          
            query = "Select username, password from user";
            rs = st.executeQuery(query);
           
            rs.beforeFirst();
            while (rs.next())
            {
                DBusername = rs.getString("username");
                DBpassword = rs.getString("password");
        
                if (username.equalsIgnoreCase(DBusername) && password.equals(DBpassword))
                {
                    conn.close();                
                    System.out.println("Login Succesfull");
                    loginSuccess = true;
                    return true;
                }
            }
            
            conn.close();
        
            } catch (SQLException ex){
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Oops, Couldn't connect to the server. Error 001");
            }
        
        //PROMT ALERT message for invalid login 
        JOptionPane.showMessageDialog(null, "Invalid username/password combination. Please try again");
        System.out.println("Login unsucesfull");
        loginSuccess = false;
        return false; //if no matching pair found or connection failed don't allow login.
    }
    
    /****************************************************************************
     * 
     * Queries data store to search for matching username and password                                                                         
     * @return integer 1 for admin account, integer 2 for staff, 0 and error for not found                                            
     */
    public int validateUser()
    {
        String query, DBuser_type;
        
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://" + host
                    + "/" + database + "?"
                    + "user=" + user
                    + "&password=" + pass);
        
            st = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
          
            query = "Select user_type from user where username = '" + username + "'";
            rs = st.executeQuery(query);
            rs.first();
            DBuser_type = rs.getString("user_type");
           
        
            if (DBuser_type.equals("1"))
            {
                System.out.println("Admin Account");
                return 1;
                //load admin view
            }
            
            if (DBuser_type.equals("2"))
            {
                System.out.println("Staff Account");
                return 2;
            }
            
            conn.close();
        
            } catch (SQLException ex){
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Validation error. Could not find user_type. ERROR 003");}
        return 0;
    } 
    
    public void actionPerformed(ActionEvent e) //Function to handle events from the GUI's
    {
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        if (cmd.equals("Submit Button"))
        {
            System.out.println("Submit pressed!");
           
        }
    }
}

