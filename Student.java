/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;

/**
 *
 * @author ChrisGoodman
 */
public class Student
{
    private String first_name;
    private String last_name;
    private String student_id;
    private String email;
    private String image_url;
    private int time_in;
    
    Student(String fname, String lname, String id, String email, String url)
    {
        this.first_name     = fname;
        this.last_name      = lname;
        this.student_id     = id;
        this.email          = email;
        this.image_url      = url;
        this.time_in        = 0;
        
    }
    
    public void setTimeIn(int x)
    {
        this.time_in = x;
    }
    
    public int getTimeIn()
    {
        return this.time_in;
    }
    
    public String getFName()
    {
        return this.first_name;
    }
    
    public String getLName()
    {
        return this.last_name;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public String getUrl()
    {
        return this.image_url;
    }
    
    public String getID()
    {
        return this.student_id;
    }
}

