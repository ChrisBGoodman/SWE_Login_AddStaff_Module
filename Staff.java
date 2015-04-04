package SWE_Login_AddStaff_Module;

/**
 * @author ChrisGoodman, James Johnson, Dennis Smith, Ben Boaz, Sherry Wang
 * @since 03/16/2015
 */

public class Staff
{
    String staff_ID;
    String name;
    String position;
    String email;
    //ArrayList<course> courses;
    String userName;
    String passWord;
    
    Staff(String staff_id, String name, String position, String email)
    {
        this.staff_ID = staff_id;
        this.name = name;
        this.position = position;
        this.email = email;
    }
    
}

