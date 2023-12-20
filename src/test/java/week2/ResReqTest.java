package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class ResReqTest {


    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.91.142.69:8000";

    }

    RequestSpecification reqSpec = given().accept(ContentType.JSON);
    ResponseSpecification resSpec = reqSpec.expect().statusCode(200);

    @Test
    public void test1(){
        Response response = given().spec(reqSpec)
                .and().pathParam("id",2)
                .when().get("/api/spartans/{id}")
                .then().spec(resSpec).extract().response();
    }


}

