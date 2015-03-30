package SWE_Login_AddStaff_Module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ChrisGoodman, James Johnson, Dennis Smith, Ben Boaz, Sherry Wang
 * @since 03/16/2015
 */

class LoginController
{
    private String host = "localhost";
    private String database = "eattendance";
    private String user = "cgoodman";
    private String pass = "swe2015";
    String username;
    String password;
    int userType;               //Integer value used to represent if a user is Admin(1) or Staff(2) or invalid (0)
    
    
    LoginController(String u, String p)
    {
        username = u;
        password = p;
        login();
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
            rs.first();
            DBusername = rs.getString("username");
            DBpassword = rs.getString("password");
        
            if (username.equalsIgnoreCase(DBusername) && password.equals(DBpassword))
            {
                conn.close();
                System.out.println("Found match!");
                return true;
            }
            
            while (rs.next())
            {
                DBusername = rs.getString("username");
                DBpassword = rs.getString("password");
        
                if (username.equalsIgnoreCase(DBusername) && password.equals(DBpassword))
                {
                    conn.close();                
                    System.out.println("Found match!");
                    return true;
                }
            }
            
            conn.close();
        
            } catch (SQLException ex){
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);}
        
        return false; //if no matching pair found or connection failed don't allow login.
    }
    
    /****************************************************************************
     * 
     * Queries data store to search for matching username and password                                                                         
     * @return integer 1 for admin account, integer 2 for staff, integer 3 for Invalid userName/passWord                                                 
     */
    private int validateUser()
    {
        //code to query dataStore and see if user is admin or staff             
    }
    
    
}

