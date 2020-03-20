package pl.fastest.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestApiDemoqa {

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "http://restapi.demoqa.com";
    }

    @Test
    public void basicAuthenticationTest() {

        Response response = null;

        String invalidusername = "deepak";
        String invalidpassword = "";

        String validusername = "ToolsQA";
        String validpassword = "TestPassword";


        //Scenario with incorrect username & password
        response = RestAssured.given()
                .auth().basic(invalidusername, invalidpassword)
                .when()
                .get("/authentication/CheckForAuthentication");

        System.out.println("Access Unauthorized \nStatus Code :" + response.getStatusCode());
        System.out.println("Response :" + response.asString());

        System.out.println("\n---------------------------------------------------\n");

        //Scenario with correct username & password
        response = RestAssured.given()
                .auth().basic(validusername, validpassword)
                .when()
                .get("/authentication/CheckForAuthentication");
    }

    @Test
    public void getWithBasicAuthTest() {
        Response response = null;

        String validusername = "ToolsQA";
        String validpassword = "TestPassword";

        //Scenario with correct username & password
        RestAssured.given()
                .auth().basic(validusername, validpassword)
                .get("/authentication/CheckForAuthentication")
                .then()
                .log().headers();

        RestAssured.given()
                .body("{\"FirstName\":\"Piotr\"}")
                .get("/customer")
                .then().log().all();

    }
}
