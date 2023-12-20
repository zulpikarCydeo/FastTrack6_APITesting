package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Authentication {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.91.142.69:7000";
    }

    @Test
    @DisplayName("Basic Auth")
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().auth().basic("user","user")
                .when().get("/api/spartans");

        assertThat(response.statusCode(),is(200));

    }
}
