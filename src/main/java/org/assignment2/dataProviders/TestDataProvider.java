package org.assignment2.dataProviders;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDataProvider {

    public static String LOGIN_DATA_PATH = "../testData/testData.xlsx";
    @DataProvider(name = "getLoginCredentials", parallel = false)
    public Object[][] getLoginData() throws IOException {
        List<Object[]> data = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(LOGIN_DATA_PATH);

        try (Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheet("login_data");
            if (sheet == null) {
                throw new IOException("Sheet 'login_data' not found in " + LOGIN_DATA_PATH);
            }

            // Skip the header row
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();

            // Iterate through each row and read columns
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String testCaseId = row.getCell(0).getStringCellValue();
                String username = row.getCell(1).getStringCellValue();
                String password = row.getCell(2).getStringCellValue();
                String expectedResult = row.getCell(3).getStringCellValue();

                // Add the row data as an array
                data.add(new Object[]{testCaseId, username, password, expectedResult});
            }
        }

        // Convert list to Object[][] for DataProvider
        return data.toArray(new Object[0][]);
    }
}
