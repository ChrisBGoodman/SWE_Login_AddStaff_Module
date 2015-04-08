/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author z
 */
public class courseTableModel extends AbstractTableModel {
    //2D ArrayList expecting each slot contain a ArrayList of course info
    private ArrayList<ArrayList<String>> tableContent;    
    private ArrayList<String> tableTitle;

    public courseTableModel (ArrayList<ArrayList<String>> mytableContent)
    {
        tableContent = new ArrayList<>();
        tableTitle = new ArrayList<String>()
                {
                    {
                        add("Course Name");
                        add("Course Code (UG)");
                        add("Course Code (G)");
                        add("Start Time");
                        add("End Time");
                    }
                };
        this.tableContent.addAll(mytableContent);
    }
    @Override
    public int getRowCount() {
        return tableContent.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ArrayList<String> tempList = tableContent.get(rowIndex);
        return tempList.get(columnIndex);
    }
    
}
