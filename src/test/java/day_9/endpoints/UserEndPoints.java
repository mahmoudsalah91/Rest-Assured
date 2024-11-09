package day_9.endpoints;
/*
endpoints (CRUD methods implementation)
note:CRUD are Create ,Retrieve , Update ,Delete requests
 */

import day_9.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserEndPoints {


    public static Response createUser(User userPayload)
    {

        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userPayload)

                .when().post(Routes.Post_url);
        return response;
    }

    public static Response getUser(String userName)
    {
        Response response =given()
                .pathParam("username",userName)
                .when().get(Routes.Get_url);
        return response;
    }

    public static Response updateUser(String userName , User userPayload )
    {
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userPayload)
                .pathParam("username",userName)
                .when().put(Routes.Update_url);
        return response;
    }

    public static Response deleteUser(String userName)
    {
        Response response =given()
                .pathParam("username",userName)
                .when().delete(Routes.Delete_url);
        return response;
    }
}
