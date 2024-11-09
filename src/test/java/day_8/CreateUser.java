package day_8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class CreateUser {

    int id;
    @Test
    void createUser(ITestContext context)
    {
        Faker faker = new Faker();
        JSONObject data = new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","inactive");

        String BearerToken = "4bd5914e4fc1e6349b917a16b777da7f8cc41747d93b725f4f64798cdde26917";

        id=given()
                .headers("Authorization","Bearer "+BearerToken)
                .contentType("application/json")
                .body(data.toString())
                .when().post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");
        System.out.println(id);

        context.setAttribute("user_id",id);
        context.getSuite().setAttribute("user_id",id);



    }
}
