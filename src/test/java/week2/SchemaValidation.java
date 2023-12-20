package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.module.jsv.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class SchemaValidation {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.91.142.69:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",2)
                .when().get("/api/spartans/{id}")
                .then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("singleSpartanSchema.json"))
                .extract().response();


    }
}
