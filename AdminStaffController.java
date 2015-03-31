package SWE_Login_AddStaff_Module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author ChrisGoodman
 * @since 03/16/2015
 */

public class AdminStaffController implements ActionListener 
{
    private String host = "localhost";
    private String database = "eattendance";
    private String user = "cgoodman";
    private String pass = "swe2015";
    private ArrayList<Staff> staffList; //Will hold a list of all Staff Members
    private add_staff_GUI staffGUI;

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
    public void uploadStaffDataExcel() throws FileNotFoundException, IOException
    {
        String filePath;
        String extensionType = "xlsx";
        File selectedFile = null;
        FileInputStream fis;
        
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(null);
     
        Scanner sc = new Scanner(fc.getSelectedFile());
        selectedFile = fc.getSelectedFile();
        
        fis = new FileInputStream(selectedFile);
        
        filePath = selectedFile.getAbsolutePath();
        extensionType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
        
        System.out.println("Extension Type"+ extensionType);
        
        if (extensionType.equals("xlsx")) //If not an excel file, break out of the function with alert message 
        {
                         //JOptionPane.showMessageDialog(null, "Uploading");
        }
        
        else 
        {
            JOptionPane.showMessageDialog(null, "Invalid File Type. Please use an excel sheet using a .xlsx extension");
            return;
        }
            
        System.out.println(selectedFile);
        //XSSFWorkbook wb = new XSSFWorkbook(); //Causing Error Here!!!
        
        //System.out.println(cell.getStringCellValue());
       // System.out.println(rows);

    }
    
    /****************************************************************************
     * send Staff member data within the ArrayList of staff to the DataStore
     * Function will be used with the Staff Form GUI
     * @param String name, position, email, username, password 
     */
    public void uploadStaffData(String staffID, String name, String position, String email, String username, 
            String password, String user_type) throws SQLException
    {
        user_type = "1";
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        
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
        Result set data must be navigated row by row with a cursor. The cursor begins before thefirst row with
        beforeFirst(). Loop will move cursor down row by row till
        reaching end of the table  storing each record found in a row
        */
        String query = "Select * from STAFF";
        String name, position, email;
        rs = st.executeQuery(query);                  
       
        rs.beforeFirst();
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
    
    
    public void actionPerformed(ActionEvent e) //Function to handle events from the GUI's
    {
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        if (cmd.equals("Add Staff"))
        {
            System.out.println("Add Staff Button Clicked, staffFormGUI should load on");
            //loadStaffFormGUI();
        }
        
        if (cmd.equals("Upload Staff"))
        {
            System.out.println("Upload Staff Button Clicked");
            try{
                
                uploadStaffDataExcel();
                
            } catch (IOException ex)
            {Logger.getLogger(AdminStaffController.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }
}

