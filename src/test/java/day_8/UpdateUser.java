package day_8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UpdateUser {
    @Test
    void updateUser(ITestContext context)
    {
        Faker faker = new Faker();
        JSONObject data = new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","female");
        data.put("email",faker.internet().emailAddress());
        data.put("status","active");

        String BearerToken = "4bd5914e4fc1e6349b917a16b777da7f8cc41747d93b725f4f64798cdde26917";

        // int id = (int) context.getAttribute("user_id");    // for execute in test level in xml
        int id = (int) context.getSuite().getAttribute("user_id");    // for execute in test level in xml
        given()
                .headers("Authorization","Bearer "+BearerToken)
                .contentType("application/json")
                .body(data.toString())
                .pathParam("id",id)

                .when().put("https://gorest.co.in/public/v2/users/{id}")

                .then()
                .statusCode(200)
                .log().all();
        System.out.println(id);

    }
}
