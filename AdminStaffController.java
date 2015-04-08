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
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
    private AdminStaffFormGUI staffFormGUI;
    boolean staffExist;                         //tells if a staff member is present in arrayList already


    public void loadStaffGUI()
    {
        staffGUI = new add_staff_GUI();
    }
    
    /****************************************************************************
     * When called will request the staffFormGUI class to load on
     */
    public void loadStaffFormGUI()
    {
        staffFormGUI = new AdminStaffFormGUI();
    }
    //---------------------------------------------------------------------------
    public void uploadStaffDataExcel() throws FileNotFoundException, IOException, InvalidFormatException, SQLException
    {
        String filePath;
        String extensionType = "xlsx";
        File selectedFile = null;
        FileInputStream fis;
        
        //Open the System File Explorer
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(null);
     
        //Read in a selected file from user selection
        Scanner sc = new Scanner(fc.getSelectedFile());
        selectedFile = fc.getSelectedFile();
        fis = new FileInputStream(selectedFile);
        
        //Check extionsion type of the file to make sure it is excel
        filePath = selectedFile.getAbsolutePath();
        extensionType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
        System.out.println("Extension Type"+ extensionType);
        
        if (extensionType.equals("xlsx")) //If not an excel file, break out of the function with alert message 
            JOptionPane.showMessageDialog(null, "Uploading");
        
            else 
        {
            JOptionPane.showMessageDialog(null, "Invalid File Type. Please use an excel sheet using a .xlsx extension");
            return;
        }
        
        // -- Create the connection --
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        
        conn = DriverManager.getConnection("jdbc:mysql://" + host
                                + "/" + database + "?"
                                + "user=" + user
                                + "&password=" + pass);   
        
        // -- Create connection to the workbook from the file selected --        
        XSSFWorkbook wb = new XSSFWorkbook(selectedFile);
        XSSFSheet ws = wb.getSheetAt(0);
        XSSFRow row;
        
        // -- Vars for reading --
        int rows = ws.getPhysicalNumberOfRows();    //Number of rows in file
        String staff_ID, staff_name, position, email, username, password;   //Strings to hold column data
        int user_type =  1;
        int i = 1;                                  //Iterate starting from row 2 since row 1 should contain headers 
        
        
        row = ws.getRow(1); //Get Row 1 skipping row 0 containing the headers of each column
        while (row != null)
        {
            staff_ID    = row.getCell(0).toString();
            staff_name  = row.getCell(1).toString();        
            position    = row.getCell(2).toString();
            email       = row.getCell(3).toString();
            username    = row.getCell(4).toString();
            password    = row.getCell(5).toString();
            
            staffExist = false;
            for(int x = 0; x < staffList.size(); x++) //loop thru arraylist checking for matching staff ID
            {
                if (staffList.get(x).staff_ID.equals(staff_ID))
                {
                    staffExist = true; //Staff member found in arrayList
                }
            }
            
            if (staffExist == false) //If false, insert into list and DB 
            {            
                staffList.add(new Staff(staff_ID,staff_name,position,email));
                st = conn.createStatement();
                st.executeUpdate("INSERT into Staff " + "VALUES('" 
                    + staff_ID  + "','" 
                    + staff_name+ "','" 
                    + position  + "','"
                    + email     + "','" 
                    + username  + "','" 
                    + password  + "')");
        
                st = conn.createStatement();
                st.executeUpdate("INSERT into user " + "VALUES('"
                    + username + "','"
                    + password + "','"
                   + user_type+ "')");
            }
            i++; //Increment to next row 
            row = ws.getRow(i); 
        }
        conn.close();    
        JOptionPane.showMessageDialog(null, "Success! Returning to home screen");
        loadStaffGUI();
    }
    
    /****************************************************************************
     * send Staff member data within the ArrayList of staff to the DataStore
     * Function will be used with the Staff Form GUI
     * @param String name, String position, email, username, password 
     */
    public void uploadStaffData(String staffID, String name, String position, String email, String username, 
            String password, String user_type) throws SQLException
    {
        String staff_seq = null, course_code_ug = null, course_code_g = null;
        user_type = "2";
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        
        conn = DriverManager.getConnection("jdbc:mysql://" + host
                                + "/" + database + "?"
                                + "user=" + user
                                + "&password=" + pass);   
        
        //Make sure given ID is not already present in the list of staff we have
        staffExist = false;
        ArrayList<Staff> list = new ArrayList<Staff>();
        list = getStaffList();
        
        for(int x = 0; x < list.size()-1; x++) //loop thru arraylist checking for matching staff ID
            {
                if (list.get(x).staff_ID.equals(staffID))
                {
                    staffExist = true; //Staff member found in arrayList
                }
            }
        
        if(staffExist == false)
        {
            // -- INSERT into Staff --
            st = conn.createStatement();
            st.executeUpdate("INSERT into Staff " + "VALUES('" 
                + staffID + "','" 
                + name      + "','" 
                + position  + "','"
                + email     + "','" 
                + username  + "','" 
                + password  + "')");
        
            // -- INSERT into User --
            st = conn.createStatement();
            st.executeUpdate("INSERT into User " + "VALUES('"
                + username + "','"
                + password + "','"
                + user_type+ "')");
            
            // --= INSERT into staff_courses 
            /* //Find the course data associated with the name passed to it from function.
            st = conn.createStatement();
            st.executeUpdate("INSERT into staff_courses " + "VALUES('"
                    + staff_seq + "','"
                    + staffID   + "','"
                    + course_code_ug + "','"
                    + course_code_g  + "')");
            */
            
            JOptionPane.showMessageDialog(null, "Inserted");

        }
        
        if(staffExist == true)
        {
            System.out.println("Staff exist...");
            JOptionPane.showMessageDialog(null, "Oops, look like this ID is already in use. Error 002");

        }
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
        String id, name, position, email;
        rs = st.executeQuery(query);                  
       
        rs.beforeFirst();
        while(rs.next())
        {
            id       = rs.getString("staff_id");
            name     = rs.getString("staff_name");   
            position = rs.getString("position");
            email    = rs.getString("email");
            staffList.add(new Staff(id,name,position,email));
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
            loadStaffFormGUI();
        }
        
        if (cmd.equals("Upload Staff"))
        {
            System.out.println("Upload Staff Button Clicked");
            try
            {
                uploadStaffDataExcel();
            } catch (IOException ex)
            {
                Logger.getLogger(AdminStaffController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidFormatException ex)
            {
                Logger.getLogger(AdminStaffController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex)
            {
                Logger.getLogger(AdminStaffController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}

