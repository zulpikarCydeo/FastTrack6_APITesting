package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import week2.POJO.*;

import java.io.*;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostMethod {


    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.91.142.69:8000";
    }

    @Test
    @DisplayName("Post a spartan")
    public void test1(){
        // provide request body in 3 ways, string, map and pojo
        String bodyString = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Huge\",\n" +
                "  \"phone\": 3692581475\n" +
                "}";

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name","Tony");
        bodyMap.put("gender","Male");
        bodyMap.put("phone",2587413695L);

        SingleSpartan bodyPojo = new SingleSpartan();
        bodyPojo.setName("Hanna");
        bodyPojo.setGender("Female");
        bodyPojo.setPhone(3214569875L);


        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(bodyPojo)
                .when().post("/api/spartans");

        assertThat(response.path("success"),is("A Spartan is Born!"));



    }

}
