package day_2;
/*
the ways to send post request body  :-
1-hashmap >> key,value

2-org.json library  >> first need add org.json dependency in pom.xml file
note need when pass values to request body convert to String ex:.body(data.toString())

3-POJO class (pain old java object) - by create separate class contain:-
-declaration of variables used in body
-create setter and getter method for these variables
-create object from this class to call these setter methods with values and pass in request body

4-external json file :-
-create file object  pass the path of json file
-create reader object pass the object of file class
-create JSONTokener object pass the object of reader class
-create JSONObject  object pass the object of JSONTokener class
note need when pass values to request body convert to String ex:.body(data.toString())

note ways 1,3 prefer in small data but 3 prefer than 1

validation in .then section
-.statusCode(201)
-.body("name",equalTo("ahmed"))
-.header("Content-Type",equalTo("application/json"))

 */

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class waysSendBodyInPostRequest {

    String id;


    //@Test(priority = 1)
    void hashMap()
    {

        // can generate data by ways:

        //1-hashmap >> key,value
        HashMap data = new HashMap();
        data.put("name","ahmed");
        String[] coursesArr ={"soupUi","robot"};
        data.put("courses",coursesArr);


        id=given()
                .contentType("application/json")
                .body(data.toString())

         .when().post("http://localhost:3000/students")
                .jsonPath().getString("id");


    }
    //@Test(priority = 1)
    void orgJson()
    {

        //2-org.json library
        JSONObject data =new JSONObject();
        data.put("name","ahmed");
        String[] coursesArr ={"robot","C+"};
        data.put("courses",coursesArr);

        id=given()
                .contentType("application/json")
                .body(data.toString())

                .when().post("http://localhost:3000/students")
                .jsonPath().getString("id");


    }

    //@Test(priority = 1)
    void POJOClass() {

        //3-POJOClass
        Post_Pojo data = new Post_Pojo();
        data.setName("ahmed");
        String[] CoursesArr = {"robot", "appium"};
        data.setCoursesArr(CoursesArr);
        id = given()
                .contentType("application/json")
                .body(data)

                .when().post("http://localhost:3000/students")
                .jsonPath().getString("id");
    }

    @Test(priority = 1)
    void ExternalJsonFile() throws FileNotFoundException {

        //4-External Json File

        File file =new File(System.getProperty("user.dir")+"/src/test/java/day_2/data.json");
        FileReader reader = new FileReader(file);
        JSONTokener jsonTokener = new JSONTokener(reader);
        JSONObject data = new JSONObject(jsonTokener);

        id = given()
                .contentType("application/json")
                .body(data.toString())

                .when().post("http://localhost:3000/students")
                .jsonPath().getString("id");
    }

    @Test(priority = 2)
    void getStudents()
    {

        given()

                .when().get("http://localhost:3000/students/")

                .then()
                .statusCode(200)
                .body("name[2]",equalTo("Stephany"))
                .header("Content-Type",equalTo("application/json"))
                .log().all();

    }
    @Test(priority = 3)
    void deleteStudent()
    {
        given()

                .when().delete("http://localhost:3000/students/"+id)

                .then()
                .statusCode(200)
                .log().all();



    }
}
