package com.automation.tests.day25_excel_io;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ReadDataFromExcel {

    @Test
    public void readExcelFileTest() throws IOException {
        //we need to get a file as an obj
        File file = new File("VytrackTestUsers.xlsx");
        //obj that represents excel file
        Workbook workbook = WorkbookFactory.create(file);
        //get QA1-short
        Sheet workSheet = workbook.getSheet("QA1-short");
        //get 1th row
        Row firstRow = workSheet.getRow(0);
        //get 1th cell
        Cell firstCell = firstRow.getCell(0);
        //get string value
        String value = firstCell.getStringCellValue();

        String secondCellValue = firstRow.getCell(1).getStringCellValue();

        System.out.println(value);
        System.out.println(secondCellValue);

        int lastCell = firstRow.getLastCellNum();

        System.out.println("###########");

        for (int i=0; i<lastCell; i++){
            System.out.print(firstRow.getCell(i) + " | ");
        }

        //Index of last row. Last row is 16th --> index is 15
        int numberOfRows = workSheet.getLastRowNum()+1;
        //returns how many rows
        int numberOfRows2 = workSheet.getPhysicalNumberOfRows();

        System.out.println("Number of rows: " +numberOfRows);
        System.out.println("Number of rows 2: " +numberOfRows2);

        System.out.println("############");

        for (int row = 0; row < workSheet.getPhysicalNumberOfRows(); row++) {
            for (int cell = 0; cell < workSheet.getRow(row).getLastCellNum(); cell++) {
                String cellValue = workSheet.getRow(row).getCell(cell).getStringCellValue();
                System.out.print(cellValue + " | ");
            }
            System.out.println();
        }

    }

//    @Test
//    public void excelUtilityTest() {
//        String path = "VytrackTestUsers.xlsx";
//        String spreadSheet = "QA1-all";
//        ExcelUtil excelUtil = new ExcelUtil(path, spreadSheet);
//        //https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
////        excelUtil.getDataList().forEach(System.out::println);
//
//        for (Map<String, String> record : excelUtil.getDataList()) {
//            System.out.println(record);
//        }
//    }
//
//    @Test
//    public void getColumnNamesTest() {
//        String path = "VytrackTestUsers.xlsx";
//        String spreadSheet = "QA1-short";
//        ExcelUtil excelUtil = new ExcelUtil(path, spreadSheet);
//
//        System.out.println(excelUtil.getColumnsNames());
//    }
//
//    }

}
