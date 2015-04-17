/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;



/**
 *
 * @author ChrisGoodman
 */
public class staffCourseController 
{
    private String host = "localhost";
    private String database = "eattendance";
    private String user = "cgoodman";
    private String pass = "swe2015";
    private String username;
           
    private ArrayList<Staff> staffList; //Will hold a list of all Staff Members
    private ArrayList<Course> courseList;
    private ArrayList<Student> studentList;
    private add_staff_GUI staffGUI;
    private AdminStaffFormGUI staffFormGUI;
    boolean staffExist;
    
    /****************************************************************************
     * When called will return the ArrayList of all students                                                                         *
     * @return ArrayList of all Students                                   
     */
    
    
    public void loadScanGUI(String courseCode)
    {
        scanGUI sg = new scanGUI();
    }
    
    public void loadStaffCourseGUI()
    {
        
    }
    
    public void setUsername(String x)
    {
        username = x;
    }
    
    public String getUsername()
    {
        return username;
    }
           
               
            
    
    public String[] getStaffCoursesTaught(String username) throws SQLException 
    {

        System.out.println("Username being used to search for staff_id to find courses: " + username);
        String[]courses = new String[5];

        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DriverManager.getConnection("jdbc:mysql://" + host
                                + "/" + database + "?"
                                + "user=" + user
                                + "&password=" + pass);
            
        st = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
                   
        String query = "SELECT staff_id FROM staff WHERE username = ('" + username + "')";
        String staffID;
        rs = st.executeQuery(query);                  
       
        rs.beforeFirst();
        rs.next();
        staffID = rs.getString("staff_id");
        
        System.out.println("Staff ID found to match the username: " + staffID);
        
        query = "SELECT course_code_ug FROM staff_courses WHERE staff_id = ('" + staffID + "')";
        rs = st.executeQuery(query);
        
        int count = 0;
        rs.beforeFirst();
        while (rs.next())
        {
            courses[count] = rs.getString("course_code_ug");
            System.out.println("Courses found that te given ID teaches: " + courses[count]);
            count++;
        }
        
        conn.close();
        return courses;
    }
    
    
    public ArrayList getStudentList() throws SQLException 
    {
        studentList = new ArrayList<Student>();        
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DriverManager.getConnection("jdbc:mysql://" + host
                                + "/" + database + "?"
                                + "user=" + user
                                + "&password=" + pass);
            
        st = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
                   
        String query = "Select * from STUDENT";
        String f_name, l_name, id, email, url;
        rs = st.executeQuery(query);                  
       
        rs.beforeFirst();
        while(rs.next())
        {
            f_name      = rs.getString("first_name");
            l_name      = rs.getString("last_name");   
            id          = rs.getString("student_id");
            email       = rs.getString("email");
            url         = rs.getString("image_url");
            studentList.add(new Student(f_name, l_name, id ,email ,url));
        }        
        conn.close();
        return studentList;
    }
    
    
    public ArrayList<String> getCourseStudentsID(String courseCodeUG) throws SQLException
    {
        ArrayList<String> IDList = new ArrayList<String>();
        
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DriverManager.getConnection("jdbc:mysql://" + host
                                + "/" + database + "?"
                                + "user=" + user
                                + "&password=" + pass);
            
        st = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
                   
       
        String query = "Select student_id from student_courses where course_code_ug = ('" + courseCodeUG + "')";
        String studentID;
        rs = st.executeQuery(query);                  
       
        rs.beforeFirst();
        while(rs.next())
        {
            studentID = rs.getString("student_id");
            
            IDList.add(studentID);
        }        
        conn.close();
        
        return IDList;
    }

}

