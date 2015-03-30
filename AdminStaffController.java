package SWE_Login_AddStaff_Module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 * @author ChrisGoodman, James Johnson, Dennis Smith, Ben Boaz, Sherry Wang
 * @since 03/16/2015
 */

public class AdminStaffController
{
    private String host = "localhost";
    private String database = "eattendance";
    private String user = "cgoodman";
    private String pass = "swe2015";
    private ArrayList<Staff> staffList; //Will hold a list of all Staff Members
    
   /*****************************************************************************
    * Please use JavaDoc comments like this with all methods. JavaDoc is created by typing "/**"
    * @param list parameters passed to the method if any
    * @return list values returned by the method if any 
    * 
    * When called will request the adminStaffGUI class to load on
    */
    public void loadStaffGUI()
    {
        //code to call and load staffGUI
    }
    
    /****************************************************************************
     * When called will request the staffFormGUI class to load on
     */
    public void loadStaffFormGUI()
    {
        //code to call and load staffFormGUI
    }
    //---------------------------------------------------------------------------
    public void uploadStaffDataExcel()
    {
        String filePath, extensionType = null;
        
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(null);
        
        
        
        try
        {
            Scanner sc = new Scanner(fc.getSelectedFile());
            File selectedFile = fc.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            extensionType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());


        } catch (FileNotFoundException ex){
            Logger.getLogger(AdminStaffController.class.getName()).log(Level.SEVERE, null, ex);}
       
        
        
        
        
        
        
        
        
    }
    
    /****************************************************************************
     * send Staff member data within the ArrayList of staff to the DataStore
     * @param String name, position, email, username, password 
     */
    public void uploadStaffData(String staffID, String name, String position, String email, String username, 
            String password) throws SQLException
    {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        
        staffID = "qwe890";
        name = "Ben Diaz";
        position = "Teacher";
        email = "bdiaz1@cub.uca.edu";
        username = "bdiaz1";
        password = "123456";
        int user_type = 1;

        conn = DriverManager.getConnection("jdbc:mysql://" + host
                                + "/" + database + "?"
                                + "user=" + user
                                + "&password=" + pass);   
        
        st = conn.createStatement();
        st.executeUpdate("INSERT into Staff " + "VALUES('" 
                + staffID + "','" 
                + name      + "','" 
                + position  + "','"
                + email     + "','" 
                + username  + "','" 
                + password  + "')");
        
        st.executeUpdate("INSERT into User " + "VALUES('"
                + username + "','"
                + password + "','"
                + user_type+ "','");
        
        conn.close();
    }
    
    /****************************************************************************
     * When called will return the ArrayList of all staff members.                                                                         *
     * @return ArrayList of all Staff members by name, position, and email                                   
     */
    public ArrayList getStaffList() throws SQLException 
    {
        staffList = new ArrayList<Staff>();        
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DriverManager.getConnection("jdbc:mysql://" + host
                                + "/" + database + "?"
                                + "user=" + user
                                + "&password=" + pass);
            
        st = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
                   
        /*
        Use the statement to execute a query on the database and store the data in the result set. 
        Result set data must be navigated row by row with a cursor. The cursor begins in the first() row 
        and stores neccesary data before entering while loop. Loop will move cursor down row by row till
        reaching end of the table 
        */
        String query = "Select * from STAFF";
        String name, position, email;
        rs = st.executeQuery(query);                  
        rs.first();                                         
        name     = rs.getString("staff_name"); 
        position = rs.getString("position");
        email    = rs.getString("email");        
        staffList.add(new Staff(name,position,email));
                
        while(rs.next())
        {
            name     = rs.getString("staff_name");   
            position = rs.getString("position");
            email    = rs.getString("email");
            staffList.add(new Staff(name,position,email));

        }        
        conn.close();
        return staffList;
    }
    
    public void displayStaffList()
    {
        for (int x = 0; x < staffList.size(); x++)
        {
            System.out.print(staffList.get(x).name.toString());
            System.out.print(staffList.get(x).position.toString());
            System.out.println(staffList.get(x).email.toString());
        }
    }
    
    public static void main(String[] args) throws SQLException
    {
        AdminStaffController asc = new AdminStaffController();
        //asc.getStaffList();
        //asc.displayStaffList();
        //asc.uploadStaffData(null, null, null, null, null, null);
        //asc.displayStaffList();
        //asc.uploadStaffDataExcel();
        String x = "cgoodman1";
        String y = "475977";
        LoginController lc = new LoginController(x,y);
        System.out.println();
    }
}

