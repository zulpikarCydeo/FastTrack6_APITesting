package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class XmlPath {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.91.142.69:8000";
    }

    @Test
    @DisplayName("xml path")
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.XML)
                .when().get("/api/spartans");

        io.restassured.path.xml.XmlPath xmlPath = response.xmlPath();
        String name1 = xmlPath.getString("List.item[0].name");
        System.out.println(name1);

    }
}
