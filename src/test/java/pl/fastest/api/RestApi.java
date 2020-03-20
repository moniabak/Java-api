package pl.fastest.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestApi {

    @Test
    public void getRestPrintTest() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given().contentType(ContentType.JSON);
        Response response = httpRequest.get("/Hyderabad");
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

//        JsonPath jsonPath = response.jsonPath();
//        String city = jsonPath.get("City");
//        System.out.println("cicty " + city);
    }

    @Test
    public void getCityBDDTest() {
        RestAssured.given().
                contentType(ContentType.JSON).
                get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad").
                then().log().body();
    }

    @Test
    public void postNewCustomerTest(){
        RestAssured.given().baseUri("http://restapi.demoqa.com/customer").
                contentType(ContentType.JSON).
                body("{\"FirstName\" : \"value\"\n" +
                        "\"LastName\" : \"value\",\n" +
                        "\"UserName\" : \"value\",\n" +
                        "\"Password\" : \"value\",\n" +
                        "\"Email\"    : \"Value\"\n" +
                        "}").
                post("/register").
                then().statusCode(201);
    }

    @Test
    public void postCustomerTest() {
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Virender"); // Cast
        requestParams.put("LastName", "Singh");
        requestParams.put("UserName", "sdimpleuser2dd2011");
        requestParams.put("Password", "password1");

        requestParams.put("Email", "sample2ee26d9@gmail.com");
        request.body(requestParams.toJSONString());
        Response response = request.post("/register");

        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode, "201");
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals("Correct Success code was returned", successCode, "OPERATION_SUCCESS");
    }
}
