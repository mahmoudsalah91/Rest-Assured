package day_8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetUser {
    @Test
    void getUser(ITestContext context)
    {
       //int id = (int) context.getAttribute("user_id");    // for execute in test level in xml
        int id = (int) context.getSuite().getAttribute("user_id");    // for execute in suite level in xml

        String BearerToken = "4bd5914e4fc1e6349b917a16b777da7f8cc41747d93b725f4f64798cdde26917";
                 given()
                    .headers("Authorization","Bearer "+BearerToken)
                    .pathParam("id",id)
                .when().get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                         .statusCode(200)
                         .log().all();


    }
}
