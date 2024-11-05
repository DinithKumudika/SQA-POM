package org.assignment2.utils;

import org.assignment2.base.BaseTest;

import java.io.FileInputStream;
import java.io.IOException;

public class TestUtils extends BaseTest {
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 20;

    public static String TEST_DATA_PATH = "../testData/testData.xlsx";

    public static FileInputStream getDataFromExcel() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(TEST_DATA_PATH);
        return fileInputStream;
    }
}
