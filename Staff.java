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
public class Staff
{
    String name;
    String position;
    String email;
    //ArrayList<course> courses;
    String userName;
    String passWord;
    
    Staff(String name, String position, String email, String userName, String passWord)
    {
        this.name = name;
        this.position = position;
        this.email = email;
        this.userName = userName;
        this.passWord = passWord;
    }
    
}

