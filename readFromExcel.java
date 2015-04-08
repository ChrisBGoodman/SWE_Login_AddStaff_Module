/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;

/**
 *
 * @author z
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class readFromExcel {

    private ArrayList<ArrayList<String>> tableContentRaw;
    
    public readFromExcel()
    {
        tableContentRaw = new ArrayList<>();
    }
    
    public ArrayList<ArrayList<String>> getTableContentRaw()
    {
        this.getData();
        return tableContentRaw;
    }
    public ArrayList<ArrayList<String>> getData() {
//
        // An excel file name. You can create a file name with a full
        // path information.
        //

        String filename = "/Users/ChrisGoodman/Desktop/courseList.xls";

//
// Create an ArrayList to store the data read from excel sheet.
//
        FileInputStream fis = null;

//
// Create a FileInputStream that will be use to read the
// excel file.
//
        try 
        {
            fis = new FileInputStream(filename);

 //
            // Create an excel workbook from the file system.
//
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
//
// Get the first sheet on the workbook.
//
            HSSFSheet sheet = workbook.getSheetAt(0);

 //
            // When we have a sheet object in hand we can iterator on
// each sheet's rows and on each row's cells. We store the
// data read on an ArrayList so that we can printed the
// content of the excel to the console.
            //
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                ArrayList<String> rowData = new ArrayList<>();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    if(cell.getCellType()==0)
                    // cellType == 0 means numeric, 1 is String
                    {
                        String cellContentNumeric = Integer.toString((int)cell.getNumericCellValue());
                        rowData.add(cellContentNumeric);
                    }
                    else if (cell.getCellType()==1)
                    {
                        String cellContentString = cell.getStringCellValue();
                        rowData.add(cellContentString);
                    }
                }

                tableContentRaw.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        }
        return tableContentRaw;
    }
/***************testCode****************    
    private void run()
    {
        for (ArrayList<String> m: tableContentRaw)
        {
            for (String n: m)
            {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
    public static void main(String args[])
    {
        readFromExcel p = new readFromExcel ();
        //p.getData();
        p.run();
    }
    * ************************************/
}