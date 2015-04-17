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
public class Course
{
    private String courseName;
    private String courseCodeUG;
    private String courseCodeG;
    private int    courseSeq;
    
    Course(String name, String ug, String g, int seq)
    {
        this.courseName     = name;
        this.courseCodeUG   = ug;
        this.courseCodeG    = g;
        this.courseSeq      = seq;
    }
    
}

