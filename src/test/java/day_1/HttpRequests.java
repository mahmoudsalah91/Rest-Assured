package day_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


/*


-as rest assured use BDD write test case style by using keywords of tool that build-in by default called gherkins
-keywords that use to write testcases excite as method in rest assured:-
1-given() : contain any prerequisites of request ex:(content type - set cookies -add auth - add parameters - set headers...)
2-when () : contain body of request ex:(get - post - put - delete) body
3-then () & And() : contain validation ex:(status code - extract response - headers - cookies - response body ...)

-note:every Http request write as (@Test) TestNg annotation

-In order to use REST assured effectively it's recommended to statically import methods from the following classes:-

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

---get---
note:given()method is include when() and then() methods
so if start with given() method you need write dot(.) before .when() and .()then methods
so times no need prerequisites not need given() method so you can write just when() and then() methods without dots(.)
-such as in get request not need body sometimes so can begin with when() and then() method
note .log().all();   // to print response in log
---post----
-in post request need to body of request so before pass body should handel data pass in given()method by flow steps:
1-if body short contain two attributes with values:-
in class level (out of given()method)
-create hashmap object  ex: HashMap data =new HashMap();
-store data in this hashmap user objectName.put(key,value)
in given()method level
-.contentType("application/dataType that send with body") ex:.contentType("application/json")
-.body(data)

---catch any attribute value from response---
-create global variable (attribute) in class level ex: int id;
-catch any attribute value from response use at when()method >> jsonPath().get dataType("keyName");
-return value of keyName in given()method as syntax of given()method become keyName=given() to easy use this attribute in another response

--update---
-put request like as post request need body in given()method
can pass variable catch in post request ex:in url by concatenate ("https://reqres.in/api/users/"+id)

--delete---
-delete request like get may not need body
can pass variable catch in post request ex:in url by concatenate ("https://reqres.in/api/users/"+id)




 */
public class HttpRequests {

    int id;

    @Test(priority = 1)
    void getUsers()
    {
        given()

                .when()
                    .get("https://freetestapi.com/api/v1/students")

                .then()
                    .statusCode(200)
                    .body("page",equalTo(2))

                    .log().all();   // to print response in log
    }

    @Test(priority = 2)
    void createUser()
    {
        HashMap data =new HashMap();
        data.put("name","mahmoud");
        data.put("job","tester");

        id=given()
                .contentType("application/json")
                .body(data)

                .when()
                    .post("https://reqres.in/api/users")
                    .jsonPath().getInt("id");


                //.then()
                  //  .statusCode(201)

                    // .log().all();


    }

    @Test(priority = 3,dependsOnMethods = "createUser" )
    void updateUser()
    {
        HashMap data =new HashMap();
        data.put("name","ahmed");
        data.put("job","QA");

        given()
                .contentType("application/json")
                .body(data)

                .when()
                    .put("https://reqres.in/api/users/"+id)


                .then()
                    .statusCode(200)

                    .log().all();

    }

    @Test(priority = 4)
    void deleteUser()
    {
        given()

                .when()
                .delete("https://reqres.in/api/users/"+id)

                .then()
                .statusCode(204)

                .log().all();


    }

}
