package week1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.hamcrest.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DeSerilization {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("json to map")
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",2)
                .when().get("/api/spartans/{id}");

        Map<String,Object> spartan2 = response.as(Map.class);
        System.out.println(spartan2);

        String name = spartan2.get("name").toString();
        System.out.println(name);

        MatcherAssert.assertThat("Male",is(spartan2.get("gender").toString()));

    }

    @Test
    @DisplayName("json to list")
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        List<Map<String,Object>> responseList = response.as(List.class);
        System.out.println(responseList);

        MatcherAssert.assertThat(responseList.get(0).get("name").toString(),is("Meade"));
    }



}
