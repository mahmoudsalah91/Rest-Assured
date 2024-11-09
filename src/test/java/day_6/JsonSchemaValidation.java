package day_6;
/*
schema: is represented data types in the response
-can validate schema by:
-store schema of response as Json file in path src/resources
-in then section but the validation by > .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("fileName.json"));
note :
-JsonSchemaValidator is predefined class contain method matchesJsonSchemaInClasspath("fileName.json")
- file.json must store in path src/resources to can validate
- https://transform.tools/json-to-json-schema to generate schema from json response


note for xml schema use xsd file to store schema
-and use un .then section assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("fileName.xsd"))

- https://www.convertsimple.com/convert-xml-to-xsd-xml-schema/     to generate schema from xml response

note there is ratifications know serialization and Deserialization ( not need in automation but may ske in interview)

serialization :- concert java object to json
Deserialization: concert json to java object
 */

import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class JsonSchemaValidation {
@Test
    void JSONSchemaValidation(){

        given()

                .when().get("http://localhost:3000/books")

                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema.json"));
    }
    @Test
    void XMLSchemaValidation(){

        given()
                .accept(ContentType.XML)
                .when().get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")

                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("Schema.xsd"));
    }
}
