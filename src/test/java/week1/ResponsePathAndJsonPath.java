package week1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.given;

public class ResponsePathAndJsonPath {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("Response path method")
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",2)
                .when().get("/api/spartans/{id}");

        int id = response.path("id");
        String name = response.path("name");
        Long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("phone = " + phone);
    }

    @Test
    @DisplayName("Response path")
    public void test2(){
        Map<String,Object> searchBody = new HashMap<>();
        searchBody.put("nameContains","za");
        searchBody.put("gender","Female");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(searchBody)
                .when().get("/api/spartans/search");

        String name1 = response.path("content[0].name");
        String name2 = response.path("content[1].name");
        System.out.println("name1 = " + name1);
        System.out.println("name2 = " + name2);
    }

    @Test
    @DisplayName("json path method")
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",2)
                .when().get("/api/spartans/{id}");

        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.getString("name");
        Long phone = jsonPath.getLong("phone");
        System.out.println("name = " + name);
        System.out.println("phone = " + phone);
    }

    @Test
    @DisplayName("Json path  get list")
    public void test4(){
        Map<String,Object> searchBody = new HashMap<>();
        searchBody.put("nameContains","za");
        searchBody.put("gender","Female");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(searchBody)
                .when().get("/api/spartans/search");

        JsonPath jsonPath = response.jsonPath();
        List<String> names = jsonPath.getList("content.name");
        System.out.println(names);
    }
}
