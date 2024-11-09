package day_9.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    //Data provider 1

    @DataProvider(name ="Data")
    public String [][] getAllData() throws IOException {
        String path = System.getProperty("user.dir")+"/TestData/Userdata.xlsx";
        ExcelUtility xlUtility = new ExcelUtility(path);

        int totalRow = xlUtility.getRowCount("Sheet1");
        int totalColumn = xlUtility.getCellCount("Sheet1",1);

        String[][] apiData = new String[totalRow][totalColumn];

        for(int r = 1;r<=totalRow;r++)
        {
            for(int c=0;c<totalColumn;c++)
            {
                apiData[r-1][c]=xlUtility.getCellData("Sheet1",r,c);
            //[r-1] as in Excel row 0 is header so we ignore at for statement so start with r=1 but should relocate in array because array start with 0 so we write [r-1] in array statement
            }
        }
        return apiData;

    }

    @DataProvider(name ="userNames")
    public String [] getUserNames() throws IOException {
        String path = System.getProperty("user.dir")+"/TestData/Userdata.xlsx";
        ExcelUtility xlUtility = new ExcelUtility(path);

        int totalRow = xlUtility.getRowCount("Sheet1");
        int totalColumn = xlUtility.getCellCount("Sheet1",1);

        String apiData [] = new String[totalRow];

        for(int r = 1;r<=totalRow;r++)
        {
            apiData[r-1]=xlUtility.getCellData("Sheet1",r,1);
            //[r-1] as in Excel row 0 is header so we ignore at for statement so start with r=1 but should relocate in array because array start with 0 so we write [r-1] in array statement
        }
        return apiData;

    }

}
