package day_9.test;

import day_9.endpoints.UserEndPoints;
import day_9.payload.User;
import day_9.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {

    User userPayload;


    @Test(priority = 1,dataProvider="Data",dataProviderClass=DataProviders.class)
    public void testPostUser(String userID,String userName,String fName,String lName,String userEmail,String pass,String phone)
    {
        userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(pass);
        userPayload.setPhone(phone);


        Response response = UserEndPoints.createUser(userPayload);
        response.then()
                .statusCode(200)
                .log().all();
        //or
        Assert.assertEquals(response.statusCode(),200);

    }

    @Test(priority = 2,dataProvider="userNames",dataProviderClass = DataProviders.class)
    public void testGetUser(String userName)
    {
        Response response =UserEndPoints.getUser(userName);
        response.then()
                .statusCode(200)
                .log().all();
        //or
        Assert.assertEquals(response.statusCode(),200);

    }

    @Test(priority = 3,dataProvider="userNames",dataProviderClass=DataProviders.class)
    public void testUpdateUser(String userName)
    {
        //update data using userPayload

        userPayload.setFirstName("mahmoud");
        userPayload.setLastName("Ahmed");
        userPayload.setEmail("a@gamil.com");

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

    @Test(priority = 4,dataProvider="userNames",dataProviderClass=DataProviders.class)
    public void testDeleteUser(String userName)
    {

        Response response =UserEndPoints.deleteUser(userName);
        response.then()
                .statusCode(200)
                .log().all();
        //or
        Assert.assertEquals(response.statusCode(),200);

    }
}
