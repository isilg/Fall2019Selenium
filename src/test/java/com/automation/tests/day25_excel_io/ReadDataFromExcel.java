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
    }

}
