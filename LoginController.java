package SWE_Login_AddStaff_Module;

/**
 * @author ChrisGoodman, James Johnson, Dennis Smith, Ben Boaz, Sherry Wang
 * @since 03/16/2015
 */

class LoginController
{
    int userType;               //Integer value used to represent if a user is Admin(1) or Staff(2) or invalid (0)
    
    
    /****************************************************************************
     * 
     */
    private void login()
    {
        //code run when button is pushed after typing in username and password
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

