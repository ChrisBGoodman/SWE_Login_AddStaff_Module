/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



/**
 *
 * @author z
 */
public class adminCourseController implements ActionListener {

    private adminCourseGUI courseGUI;
    private courseFormGUI formGUI;
    private readFromExcel excelReader;
    private ArrayList<ArrayList<String>> list; 
    
    
    public adminCourseController() {
        

    }

    public void run() {
        excelReader = new readFromExcel();
        list = new ArrayList<ArrayList<String>>();
        list = excelReader.getData();
        //courseGUI = new adminCourseGUI(excelReader.getData());
        /* Create and display the form */
        //courseGUI.setVisible(false);

    }
    
    public ArrayList<ArrayList<String>> getCourses()
    {
        return list;
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        formGUI = new courseFormGUI();
        if (e.getActionCommand() == "Add Course") {

            this.formGUI.setVisible(true);

        }
        else if (e.getActionCommand() == "Register") {
            //code for passing the data to model
            formGUI.removeAll();
        }
        else if (e.getActionCommand() == "Cancel") {
            formGUI.setVisible(false);
        }

    }

}
