/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author ChrisGoodman
 */
public class mainRunnable
{
    public static void main(String args[]) throws IOException, FileNotFoundException, InvalidFormatException, SQLException
    {
        
        LoginGUI lg = new LoginGUI();

        //add_staff_GUI staffGUI = new add_staff_GUI(); //Use this to view our GUI's

        //AdminStaffController asc = new AdminStaffController();
        //asc.getStaffList();
        //asc.displayStaffList();
            //asc.uploadStaffData(staffID, name, position, email, username, password); 
            //asc.uploadStaffDataExcel();   //work in progress will be finished soon
            //asc.displayStaffList();
        
    }
}

