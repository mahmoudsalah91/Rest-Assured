package day_9.endpoints;

import day_9.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;



/*

endpoints (CRUD methods implementation)
note:CRUD are Create ,Retrieve , Update ,Delete requests

 Read from properties file by:-
 loading config properties file by :-
 1-by FileReader
        public static Properties propertyFile;
        FileReader file = new FileReader(System.getProperty("user.dir")+"/src/test/resources/routes.properties");
        propertyFile = new Properties();
        propertyFile.load(file);

  2-by method getting url from properties file in resource package

    static ResourceBundle getUrl()
    {
        ResourceBundle routes =ResourceBundle.getBundle("routes");
        return routes;
    }

 */
public class UserEndPointsWithPropertiesFile {
    public static Properties propertyFile;

    //2-by method getting url from properties file in resource package
     static ResourceBundle getURL(){
            ResourceBundle routes = ResourceBundle.getBundle("routes");
            return routes;
    }


    public static Response createUser(User userPayload) throws IOException {
       // loading config properties file:-
        //1-by FileReader
        FileReader file = new FileReader(System.getProperty("user.dir")+"/src/test/resources/routes.properties");
        propertyFile = new Properties();
        propertyFile.load(file);


        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userPayload)

                .when().post(propertyFile.getProperty("Post_url"));
        return response;
    }

    public static Response getUser(String userName)
    {
        //2-by method getting url from properties file in resource package

        String get_url =getURL().getString("Get_url");


        Response response =given()
                .pathParam("username",userName)
                .when().get(get_url);
        return response;
    }

    public static Response updateUser(String userName , User userPayload )
    {
        //1-by FileReader
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userPayload)
                .pathParam("username",userName)
                .when().put(propertyFile.getProperty("Update_url"));
        return response;
    }

    public static Response deleteUser(String userName)
    {
        //2-by method getting url from properties file in resource package

        String Delete_url =getURL().getString("Delete_url");
        Response response =given()
                .pathParam("username",userName)
                .when().delete(Delete_url);
        return response;
    }
}
