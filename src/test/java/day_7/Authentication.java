package day_7;
/*
Authentication : check user valid or not
Authorization : if user valid check accessibility or permission

Authentication types : -
1-need username and password
-Basic
-Digest
-preemptive

2-need token
-Bearer token  pass in headers
-Api Key       pass in Query parameter
more secure:-
-OAuth0.1 (need more parameters)  eg:("consumeKey","consumeSecret","accessToken","TokenSecret")
-OAuth0.2 (need one parameter) eg:(Token) >> upgrade of OAuth0.1

 */

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentication {
    @Test
    void BasicAuthentication(){
        given()
           .auth().basic("postman","password")

                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test
    void DigestAuthentication(){
        given()
                .auth().digest("postman","password")

                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test
    void preemptiveAuthentication(){
        given()
                .auth().preemptive().basic("postman","password")

                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test
    void BearerTokenAuthentication(){
        String BearerToken = "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";
        given()
                //.headers("Authorization","Bearer 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")
                //or
                .headers("Authorization","Bearer "+BearerToken)

                .when().get("https://api.github.com/user")
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    void OAuth2Authentication(){
        given()
                .auth().oauth2("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")

                .when().get("https://api.github.com/user")
                .then()
                .statusCode(200)
                .log().all();
    }

    // syntax of OAuth1 Authentication as there is Demo  api to test
    void OAuth1Authentication(){
        given()
                .auth().oauth("consumeKey","consumeSecret","accessToken","TokenSecret")

                .when().get("https://api.github.com/user")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void ApiKeyAuthentication(){
        String ApiKey = "88fa8e75c38f29893d8a7ec3f54dc02b";
        given()
                .queryParam("appid","90dad8e4e56adb6571898dc9604e35e6")

                .when().get("http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5")
                .then()
                .log().all()
                .statusCode(200);


    }
}
