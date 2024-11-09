package day_5;

/*
-validate response by different approach:-
1-by use static org.hamcrest.Matchers :- that can use by in .then() method can validate simple data (limited validation)like:-
-.statusCode(201)
-.body("pets.Pet[0].status",equalTo("available"))
-.header("Content-Type",equalTo("application/xml"))

2-by use TestNg assertion :- that can use in .when() method can validate more complex data by these steps:-
-capture response by -in given() section capture all response and restore is variable its types is Response ex: Response res =given()
-in .when() section can validate by use:-

 1-TestNg assertions directly like:
status code >>         Assert.assertEquals(res.statusCode(),200);
header >>             Assert.assertEquals(res.header("Content-Type"),"application/xml");
body >>              Assert.assertEquals(res.xmlPath().get("pets.Pet[0].status").toString(),"available");

 2-search in the response body of xml by:-
-option if response not xml format add .accept(ContentType.XML) in given section
-catch the xml path :- take object from XmlPath ex: XmlPath xml = new  XmlPath(res.asSting());
-use this object to store the response in the List String ex: List<String>pets=xml.getPath(pets.Pet.name);
-search in response path that catch by for loop in the data then validate
note store status in boolean type as false and use in if condition , you should write boolean status = false; for each validation

 */




import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ValidateResponseXml {

    @Test
    void approach1ValidateResponse()
    {
        given()

                .accept(ContentType.XML)
                .when().get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")

                .then()
                .statusCode(200)
                //.body("pets.Pet[0].name",equalTo("doggie"))
                .body("pets.Pet[0].status",equalTo("available"))
                .header("Content-Type",equalTo("application/xml"))
                .log().all();

    }

    @Test
    void approach2ValidateResponse()
    {
        Response res=given()
                .accept(ContentType.XML)


                .when().get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");

        //1-by use directly TestNg

             Assert.assertEquals(res.statusCode(),200);
             Assert.assertEquals(res.header("Content-Type"),"application/xml");
             Assert.assertEquals(res.xmlPath().get("pets.Pet[0].status").toString(),"available");



        //2-by capture response body

        boolean status = false;   // as by default it is false ,use it to validate if json data contain that title status be true

        XmlPath xml = new XmlPath(res.asString());

        List<String> petsName =xml.getList("pets.Pet.name");
        System.out.println(petsName.size());

        for(String petName:petsName)
        {

            if(petName.equals("doggie"))
            {
                status=true;
                break;
            }
        }


        Assert.assertEquals(status,true,"the name not exist");

            boolean s =false;
        List<String>categories = xml.getList("pets.Pet.category.name");
        System.out.println(categories.size());
        for(String categoryName:categories)
        {
            if(categoryName.equals("dog"))
            {
                s=true;
                break;
            }
        }
        Assert.assertEquals(s,true,"the categoryName not exist");
    }


}
