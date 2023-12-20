package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import week2.POJO.*;

import java.util.*;

import static io.restassured.RestAssured.given;

public class DeserilizationPojo {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.91.142.69:8000";
    }

    @Test
    @DisplayName("Deserialization with custom class")
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",2)
                .when().get("/api/spartans/{id}");

        SingleSpartan spartan2 = response.as(SingleSpartan.class);

        System.out.println("spartan2.getId() = " + spartan2.getId()); // 2
        System.out.println("spartan2.getName() = " + spartan2.getName());// nels
        Assertions.assertEquals("Male",spartan2.getGender());
    }

    @Test
    @DisplayName("Deserialization with custom class")
    public void test2(){
        Map<String,Object> searchBody = new HashMap<>();
        searchBody.put("nameContains","a");
        searchBody.put("gender","Female");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(searchBody)
                .when().get("/api/spartans/search");

        SearchSpartan searchSpartan = response.as(SearchSpartan.class);
        Assertions.assertEquals(42,searchSpartan.getTotalElement());
    }
}
