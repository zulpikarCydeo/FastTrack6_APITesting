package week1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.given;

public class Parameters {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("Search by id -- path parameter")
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",2)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();
    }

    @Test
    @DisplayName("Search by id -- path parameter")
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/2");

        response.prettyPrint();
    }

    @Test
    @DisplayName("Query param")
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","za")
                .when().get("/api/spartans/search");

        response.prettyPrint();
    }

    @Test
    @DisplayName("Query param -- with map")
    public void test4(){
        Map<String,Object> searchBody = new HashMap<>();
        searchBody.put("nameContains","za");
        searchBody.put("gender","Female");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(searchBody)
                .when().get("/api/spartans/search");

        response.prettyPrint();
    }
}
