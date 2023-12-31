package week1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

public class FirstTest {

    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("http://54.152.219.47:8000/api/spartans");

        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);
        response.prettyPrint();
    }
}
