package day_9.utilities;
/*
    to read data from Excel sheet using selenium it is not possible
    so we use third party integration tool put in pom xml file called (Apache POI Common ,Apache POI API Based On OPC and OOXML Schemas)

    Excel file ---> workbook ---> sheets ---> cells
    note use this method to access these steps to reach to cell
    XSSFWorkbook ----> workbook
    XSSFSheets   ----> sheets
    XSSFRow      ----> row
    XSSFCell     ----> cell

    note there are 2 classes to user
    1-for reading > FileInputStream
    2-for writing > FileOutputStream


 */

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {
    public FileInputStream file;
    public FileOutputStream fileOut;
    public  XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtility(String path){
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException {
        // open Excel file
        file = new FileInputStream(path);
        // open work book
        workbook = new XSSFWorkbook(file);
        //get sheet with sheet name or index
        sheet = workbook.getSheet(sheetName);   // or workbook.getSheetAt(0)
        // get account of rows and columns
        int totalRows = sheet.getLastRowNum();  //3
        workbook.close();
        file.close();
        return totalRows;

    }
    public int getCellCount(String sheetName,int rowNum) throws IOException {
        // open Excel file
        file = new FileInputStream(path);
        // open work book
        workbook = new XSSFWorkbook(file);
        //get sheet with sheet name or index
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int totalCells = row.getLastCellNum();
        workbook.close();
        file.close();
        return totalCells;
    }
    public String getCellData(String sheetName,int rowNum,int column) throws IOException {
        // open Excel file
        file = new FileInputStream(path);
        // open work book
        workbook = new XSSFWorkbook(file);
        //get sheet with sheet name or index
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(column);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell); // return the format Cell Value as String regardless its Datatype in EXCEL sheet
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        file.close();
        return data;
    }
    public void setCellData(String sheetName,int rowNum,int column,String data) throws IOException {

        File xlfile = new File(path);
        if (!xlfile.exists())  // if file not exists then create a new file
        {
            workbook = new XSSFWorkbook();
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
        }
        file = new FileInputStream(path);
        workbook = new XSSFWorkbook(file);

        if (workbook.getSheetIndex(sheetName) == -1) //if sheet not exists then create a new sheet
            workbook.createSheet(sheetName);
        sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rowNum) == null)          //if row not exists then create a new row
            sheet.createRow(rowNum);
        row = sheet.getRow(rowNum);

        cell = row.createCell(column);
        cell.setCellValue(data);
        fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        workbook.close();
        file.close();
        fileOut.close();
    }

    public void fileGreenColor(String sheetName,int rowNum,int column) throws IOException{
        file = new FileInputStream(path);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);
        cell = row.getCell(column);

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fileOut);
        workbook.close();
        file.close();
        fileOut.close();
    }

    public void fileRedColor(String sheetName,int rowNum,int column) throws IOException{
        file = new FileInputStream(path);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);
        cell = row.getCell(column);

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fileOut);
        workbook.close();
        file.close();
        fileOut.close();
    }


}

