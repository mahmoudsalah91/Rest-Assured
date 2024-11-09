package day_3;
/*
parameters:-
ex:url="https://reqres.in/api/users?page=2&id=8"
1-path parameters :- where should go - part of url request place after / ex:- users
-path parameters have only value
2-query parameters :- filter the data - part of url request place after ?   ex:- page=2 , id=8
-query parameters have name and value

- to pass parameters during send request
1- pass path parameters:
-in .given section write .pathParam("parameterName","value") only value already in url
ex: .pathParam("myPath","users")
-pass path parameter through url as {parameterName} ex: "https://reqres.in/api/{myPath}"
2- pass path parameters:
-in .given section write .queryParam("Name",value) name and value already in url
ex: .queryParam("page",2) - .queryParam("id",8)
-query parameters not need path in url as it send with his name and value in request body

 */

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQuarryParameters {

    @Test
    void testPathAndQueryParameters()
    {
        given()
                .pathParam("myPath","users")
                .queryParam("page",2)
                .queryParam("id",8)

                .when().get("https://reqres.in/api/{myPath}")

                .then()
                .statusCode(200)
                .body("data.first_name",equalTo("Lindsay"))
                .log().all();

    }

}
