package day_8;


import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DeleteUser {
    @Test
    void deleteUer(ITestContext context)
    {
        // int id = (int) context.getAttribute("user_id");    // for execute in test level in xml
        int id = (int) context.getSuite().getAttribute("user_id");    // for execute in test level in xml
        String BearerToken = "4bd5914e4fc1e6349b917a16b777da7f8cc41747d93b725f4f64798cdde26917";
        given()
                .headers("Authorization","Bearer "+BearerToken)
                .contentType("application/json")
                .pathParam("id",id)

                .when().delete("https://gorest.co.in/public/v2/users/{id}")

                .then()
                .statusCode(204)
                .log().all();

    }
}
