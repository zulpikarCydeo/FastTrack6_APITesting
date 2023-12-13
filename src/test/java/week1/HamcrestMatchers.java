package week1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.hamcrest.*;
import org.junit.jupiter.api.*;
import org.junit.platform.engine.support.discovery.*;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchers {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("Hamcrest Intro")
    public void test1(){
        MatcherAssert.assertThat(10,is(10));
        MatcherAssert.assertThat(5,not(10));
        MatcherAssert.assertThat("null",notNullValue());

        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        MatcherAssert.assertThat(numbers,everyItem(is(greaterThan(0))));
    }

    @Test
    @DisplayName("Sparan Test")
    public void test2(){
        Map<String,Object> searchBody = new HashMap<>();
        searchBody.put("nameContains","za");
        searchBody.put("gender","Female");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(searchBody)
                .when().get("/api/spartans/search");

        JsonPath jsonPath = response.jsonPath();
        List<String> names = jsonPath.getList("content.name");

        MatcherAssert.assertThat("Lorenza",is(jsonPath.getString("content[0].name")));
        MatcherAssert.assertThat(names,everyItem(notNullValue()));

    }

    @Test
    @DisplayName("validate chain")
    public void test3(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",2)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().body("id",is(2),
                        "name",is("Nels"),
                        "gender",not("Female"));


    }
}
