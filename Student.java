/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;

import java.util.ArrayList;

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
    private int time_in_hour;
    private int time_in_min;
    private String status;
    
    Student(String fname, String lname, String id, String email, String url)
    {
        this.first_name     = fname;
        this.last_name      = lname;
        this.student_id     = id;
        this.email          = email;
        this.image_url      = url;
        this.time_in_hour   = 0;
        this.time_in_hour   = 0;
        this.status         = "Absent";
    }
    
    Student()
    {
        
    }
    
    public void setTimeInHour(int x)
    {
        this.time_in_hour = x;
    }
    
    public void setStatus(String x)
    {
        this.status = x;
    }
    
    public String getStatus()
    {
        return this.status;
    }
    
    public int getTimeInHour()
    {
        return this.time_in_hour;
    }
    
    public void setTimeInMin(int x)
    {
        this.time_in_min = x;
    }
    
    public int getTimeInMin()
    {
        return this.time_in_min;
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
    
    public int findIDInList(String IDToSearch, ArrayList<Student> list )
    {
        for ( int x = 0; x < list.size(); x++)
        {
            if (list.get(x).student_id.equals(IDToSearch))
            {
                System.out.println("Found at " + x);
                return x;
            }
        }
        
        return -1;
    }
}

