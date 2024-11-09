package day_9.test;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import day_9.endpoints.UserEndPoints;
import day_9.payload.User;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Tests {
    Faker faker;
    User userPayload;
@BeforeClass
    public void setupData()
    {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setPassword(faker.internet().password(5,10));
    }
    @Test(priority = 1)
    public void testPostUser()
    {
        Response response =UserEndPoints.createUser(userPayload);
        response.then()
                .statusCode(200)
                .log().all();
        //or
        Assert.assertEquals(response.statusCode(),200);

    }

    @Test(priority = 2)
    public void testGetUser()
    {
        Response response =UserEndPoints.getUser(userPayload.getUsername());
        response.then()
                .statusCode(200)
                .log().all();
        //or
        Assert.assertEquals(response.statusCode(),200);

    }

    @Test(priority = 3)
    public void testUpdateUser()
    {
        //update data using userPayload

        userPayload.setFirstName("mahmoud");
        userPayload.setLastName("Ahmed");
        userPayload.setEmail(faker.internet().emailAddress());

        Response response =UserEndPoints.updateUser(userPayload.getUsername(),userPayload);
        response.then()
                .statusCode(200)
                .log().all();
        //or
        Assert.assertEquals(response.statusCode(),200);

        //check data after update
        Response responseAfterUpdate =UserEndPoints.getUser(userPayload.getUsername());
        responseAfterUpdate.then()
                .statusCode(200)
                .log().all();

        //can validate response body by
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("firstName").toString(),"mahmoud");

    }

    @Test(priority = 4)
    public void testDeleteUser()
    {

        Response response =UserEndPoints.deleteUser(userPayload.getUsername());
        response.then()
                .statusCode(200)
                .log().all();
        //or
        Assert.assertEquals(response.statusCode(),200);

    }

}
