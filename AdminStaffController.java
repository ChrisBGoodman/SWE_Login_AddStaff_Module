package SWE_Login_AddStaff_Module;

import java.util.ArrayList;

/**
 * @author ChrisGoodman, James Johnson, Dennis Smith, Ben Boaz, Sherry Wang
 * @since 03/16/2015
 */

public class AdminStaffController
{
    ArrayList<Staff> staffList; //Will hold a list of all Staff Members
    
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
    
    /****************************************************************************
     * send Staff member data within the ArrayList of staff to the DataStore
     * @param ArrayList of staff  
     */
    public void uploadStaffData(ArrayList<Staff> st)
    {
        //code to upload staff data to data store
    }
    
    /****************************************************************************
     * When called will return the ArrayList of all staff members.                                                                         *
     * @return ArrayList of all Staff members                                   
     */
    public ArrayList getStaffList() 
    {
        //code to retreive staff from DataStore
    }
    
    
    
    
    
    
    
    
    
}

