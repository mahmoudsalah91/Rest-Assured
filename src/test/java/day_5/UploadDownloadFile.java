package day_5;
/*
-upload file
-identify the file path that want to upload by pass this file path in File class ex:File myFile = new(System.getProperty(user.dir)/filePath)
-in given section:-
for upload signal file >> file
-identify  .multiPart method ex:.multiPart("file","myFile");
for upload multi files >> files
-identify  .multiPart method ex:.multiPart("files","myFile");
-identify  .multiPart method ex:.multiPart("files","myFile");
-identify the form of data contentType as ("multipart/form-data")
 */
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UploadDownloadFile {
    @Test
    void uploadFile(){
        File myFile1 =new File(System.getProperty("user.dir")+"/src/test/java/day_5/file1.txt");
        File myFile2 =new File(System.getProperty("user.dir")+"/src/test/java/day_5/file2.txt");
        given()
                //upload single file
                .multiPart("file",myFile1)
                //upload multi files
                .multiPart("files",myFile1)
                .multiPart("files",myFile2)
                .contentType("multipart/form-data")

        .when().get("https://api.cloudinary.com/v1_1/demo/image/upload")
                .then()
                .log().all();

    }


}
