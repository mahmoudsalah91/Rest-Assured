package day_3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

/*
Cookies and Headers : is part of response can capture by:-
1-Cookies:-
---can get info (value) of single cookie by identify its key name then get value
-in given() section capture all response and restore is variable its types is Response ex: Response res =given()
-when send request in .when() method can capture the value of single cookie by identify its key name ex: res.getCookie("keyName");
-then store res.getCookie("keyName") in available as string ex: String cookie_value=res.getCookie("keyName");

---can get all cookies key with its value by :-
-in given() section capture all response and restore is variable its types is Response ex: Response res =given()
-when send request in .when() method can capture the value of all cookies ex: res.getCookies();
-then store res.getCookies() as Map with two value ex: Map<String,String> cookie_value=res.getCookies();

-can validate cookie in .then() methode by use .cookie("keyName","value")
but not cookie is dynamically change with each request send so if the value not match(test failed) that mean test is pass

1-Headers:-
---can get info (value) of single Header by identify its key name then get value
-in given() section capture all response and restore is variable its types is Response ex: Response res =given()
-when send request in .when() method can capture the value of single Header by identify its key name ex: res.getHeader("keyName");
-then store res.getHeader("keyName") in available as string ex: String Header_value=res.getHeader("keyName");

---can get all Headers key with its value by :-
-in given() section capture all response and restore is variable its types is Response ex: Response res =given()
-when send request in .when() method can capture the value of all cookies ex: res.getHeaders();
-then store res.getHeaders() as Header variable ex:  Header Headers_value=res.getHeaders();

-can validate Header in .then() methode by use .Header("keyName","value")

--log--
in .then() section can print log with different log types:-
-.log.all(); >>>> log all response (body - headers - cookies)
-.log.body(); >>>> log body of response
-.log.cookies(); >>>> log cookies of response
-.log.headers(); >>>> log headers of response

 */


import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class CookiesAndHeaders {
    @Test
    void getSingleCookie() {
        Response res = given()
                .when().get("https://www.google.com/");
        String cookie_value = res.getCookie("AEC");
        System.out.println("cookie_value of AEC is " + cookie_value);


    }

    @Test
    void getAllCookies() {
        Response res = given()
                .when().get("https://www.google.com/");
        Map<String, String> cookies_values = res.getCookies();
        System.out.println(cookies_values);

    }

    @Test
    void validateSingleCookie() {
         given()

                .when().get("https://www.google.com/")

                 .then()
                .cookie("AEC","AVYB7cqGHAmcNDqAKQfRv1_i3WhjF_P9_FkWC8iL1UR3_J9JaCRMgsD1MQ")
                .log().all();

    }

    @Test
    void getSingleHeader() {
        Response res = given()
                .when().get("https://www.google.com/");
        String Header_value = res.getHeader("Content-Type");
        System.out.println("Header_value of Content-Type is " + Header_value);


    }

    @Test
    void getAllHeaders()
    {
        Response res = given()
                .when().get("https://www.google.com/");
              Headers H =res.headers();
        System.out.println(H);
    }

    @Test
    void validateSingleHeader()
    {
        given()

                .when().get("https://www.google.com/")

                .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .and()                  // not mandatory use .and() when multi validates
                .header("Content-Encoding","gzip")
                .and()
                .header("Server","gws")
        .log().all();
    }

    @Test
    void log()
    {
        given()
                .when().get("https://www.google.com/")
                .then()
                .log().all()                    // log all response (body - headers - cookies)
                .log().body()                   //log body of response
                .log().cookies()                 // log cookies of response
                .log().headers();                 //log headers of response
    }
}
