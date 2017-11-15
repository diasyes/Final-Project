package model;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class UserIO{

    //for myLinked list
    public static void writeUsers(Object data) throws IOException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("users.dat"));
        output.writeObject(data);
    }

    public static Object readUsers() throws IOException, ClassNotFoundException{
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("users.dat"));
        return input.readObject();
    }

    //for excelFile
    public static ArrayList readExcel() throws IOException{
        String filename = "test.xls";

        // Create an ArrayList to store the data read from excel sheet.
        ArrayList sheetData = new ArrayList();
        FileInputStream fis = null;
        try {

            // Create a FileInputStream that will be use to read the excel file.
            fis = new FileInputStream(filename);

            // Create an excel workbook from the file system.
            HSSFWorkbook workbook = new HSSFWorkbook(fis);

            // Get the first sheet on the workbook.
            HSSFSheet sheet = workbook.getSheetAt(0);

            // When we have a sheet object in hand we can iterator on
            // each sheet's rows and on each row's cells. We store the
            // data read on an ArrayList so that we can printed the
            // content of the excel to the console.
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                java.util.List data = new ArrayList();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    data.add(cell);
                }

                sheetData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return sheetData;
    }
    public static void writeExcel(User user, int rowIndex) throws IOException, FileNotFoundException{
        try {
            //Get the excel file.
            FileInputStream file = new FileInputStream(new File("users.xls"));

            //Get workbook for XLS file.
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //Get first sheet from the workbook.
            //If there have >1 sheet in your workbook, you can change it here IF you want to edit other sheets.
            HSSFSheet sheet1 = workbook.getSheetAt(0);

            int actualRowIndex = rowIndex + sheet1.getLastRowNum();
            Row row = sheet1.createRow(rowIndex);

            Cell fnameColumn = row.createCell(0);
            String fname = user.getFirstName();
            fnameColumn.setCellValue(fname);
            Cell lnameColumn = row.createCell(1);
            String lname = user.getLastName();
            lnameColumn.setCellValue(lname);
            Cell usernameColumn = row.createCell(2);
            String username = user.getUsername();
            usernameColumn.setCellValue(username);
            Cell addressColumn = row.createCell(3);
            String address = user.getAddress();
            addressColumn.setCellValue(address);
            Cell cityColumn = row.createCell(4);
            String city = user.getCity();
            cityColumn.setCellValue(city);
            Cell stateColumn = row.createCell(5);
            String state = user.getState();
            stateColumn.setCellValue(state);
            Cell zipColumn = row.createCell(6);
            int zip = user.getZip();
            zipColumn.setCellValue(zip);

            //Close the excel file.
            file.close();
            //Where you want to save the updated sheet.
            FileOutputStream out = new FileOutputStream(new File("users.xls"));
            workbook.write(out);
            out.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
