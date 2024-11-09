package day_4;
/*
-validate response by different approach:-
1-by use static org.hamcrest.Matchers :- that can use by in .then() method can validate simple data (limited validation)like:-
-.statusCode(201)
-.body("name",equalTo("ahmed"))
-.header("Content-Type",equalTo("application/json"))

2-by use TestNg assertion :- that can use in .when() method can validate more complex data by these steps:-
-capture response by -in given() section capture all response and restore is variable its types is Response ex: Response res =given()
-in .when() section can validate by use:-
 1-TestNg assertions directly like:
status code >>         Assert.assertEquals(res.statusCode(),200);
header >>             Assert.assertEquals(res.header("Content-Type"),"application/json");
body >>              Assert.assertEquals(res.jsonPath().get("title[2]").toString(),"The Divine Comedy");

 2-search in the response body of json (object or Array according to json data start with'}'or']') by for loop in the data then validate
 -note store status in boolean type as false and use in if condition , you should write boolean status = false; for each validation


 */




import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ValidateResponseJson {


    @Test
    void approach1ValidateResponse()
    {
        given()

                .when().get("http://localhost:3000/books")

                .then()
                .statusCode(200)
                .body("title[2]",equalTo("The Divine Comedy"))
                .header("Content-Type",equalTo("application/json"))
                .log().all();

    }

    @Test
    void approach2ValidateResponse()
    {
        Response res=given()


                .when().get("http://localhost:3000/books");

        //1-by use directly TestNg
             /*
             Assert.assertEquals(res.statusCode(),200);
             Assert.assertEquals(res.header("Content-Type"),"application/json");
             Assert.assertEquals(res.jsonPath().get("title[2]").toString(),"The Divine Comedy");
             */


        //2-by capture response body

        boolean status = false;   // as by default it is false ,use it to validate if json data contain that title status be true
        JSONArray js = new JSONArray(res.asString()); // converting Json Array to string to be able to validate with string after that

        for(int i=0;i<js.length();i++)
        {
            String title =js.getJSONObject(i).get("title").toString();  // ArrayObject .get JsonObjects .get keyName
            if(title.equals("The Divine Comedy"))
            {
                status=true;
                break;
            }
        }
        Assert.assertEquals(status,true,"the title not exist");

        for(int i=0;i<js.length();i++)
        {
            String country =js.getJSONObject(i).get("country").toString();
            if(country.equals("Italy")){
                status=true;
                break;
            }
        }
        Assert.assertEquals(status,true,"the country not exist");

    }


}
