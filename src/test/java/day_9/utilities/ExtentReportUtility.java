package day_9.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportUtility implements ITestListener {

    public ExtentSparkReporter sparkReporter;  // UI of report
    public ExtentReports extentReporter;      // populate common info on the report
    public ExtentTest extentTest;            // create test cases enters on the report and update status of test methods

    String reportName;
    public void onStart(ITestContext context) {      // run before all test in test class

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp to specify each report
        reportName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/"+reportName);  //location of the generate report

        sparkReporter.config().setDocumentTitle("Rest Assured Automation Report");  // set title of the report
        sparkReporter.config().setReportName("API Test");                        // set name of the report
        sparkReporter.config().setTheme(Theme.STANDARD);                  // set theme of the report

        extentReporter = new ExtentReports();
        extentReporter.attachReporter(sparkReporter);

        extentReporter.setSystemInfo("Computer Name","local Host");
        extentReporter.setSystemInfo("Application",("--- API"));
        extentReporter.setSystemInfo("Environment","QA");
        extentReporter.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReporter.setSystemInfo("tester",System.getProperty("user.name"));




        List <String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty())
        {
            extentReporter.setSystemInfo("Groups", includedGroups.toString());
        }
    }


    public void onTestSuccess(ITestResult result) {  // run before each one test Passed in test class
        extentTest = extentReporter.createTest(result.getTestClass().getName());  // create new entry on the report
        extentTest.assignCategory(result.getMethod().getGroups()); // to display the groups in the report
        extentTest.log(Status.PASS , "Test Case Passed is " + result.getName());   // update status pass/fail/skip

    }

    public void onTestSkipped(ITestResult result) {  // run before each one test Skipped in test class
        extentTest = extentReporter.createTest(result.getTestClass().getName());  // create new entry on the report
        extentTest.assignCategory(result.getMethod().getGroups()); // to display the groups in the report
        extentTest.log(Status.SKIP , "Test Case Skipped is " + result.getName());
        extentTest.log(Status.INFO , "Test Case Skipped is " + result.getThrowable().getMessage());   // get exception

    }

    public void onTestFailure(ITestResult result) {  // run before each one test Failed in test class
        extentTest = extentReporter.createTest(result.getTestClass().getName());  // create new entry on the report
        extentTest.assignCategory(result.getMethod().getGroups()); // to display the groups in the report

        extentTest.log(Status.FAIL , "Test Case Failed is " + result.getName());
        extentTest.log(Status.INFO , "Test Case Failed is " + result.getThrowable().getMessage());   // get exception
/*
        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            extentTest.addScreenCaptureFromPath(imgPath);
        }catch (Exception e1){
            e1.printStackTrace();
        }

 */

    }

    public void onFinish(ITestContext context) {    // run After all test in test class
        extentReporter.flush();

        // to open report automatically once the testcases run finish
        String pathOfExtentReport = System.getProperty("user.dir")+"/Reports/"+reportName;
        File extentReporter = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReporter.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        // to send report automatically to an Email once the testcases run finish

        try {
            URL url = new URL("file:///"+System.getProperty("user.dir")+"/Reports/"+reportName);

            //Create the Email Massage
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("email@gamil.com","pass"));
            email.setSSLOnConnect(true);
            email.setFrom("email@gamil.com");  //Sender
            email.setSubject("Test Result");
            email.setMsg("Kindly note this this the Test Result Report");
            email.addTo("email@gamil.com");     // receiver
            email.attach(url ,"extent Report" ,"please check the Test Result");
            email.send();    // send the email


        } catch (Exception e) {
            e.printStackTrace();
        }

      */
    }

}
