package pl.fastest.api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;

public class WeatherGetRequests {


    @Test
    public void Test_01() {
        Response resp = when().
                get("https://samples.openweathermap.org/data/2.5/weather?q=London&appid=b6907d289e10d714a6e88b30761fae22");

        Assert.assertEquals(resp.getStatusCode(), 200);
    }

    @Test
    public void Test_02() {
        Response resp = when().
                get("https://samples.openweathermap.org/data/2.5/weather?q=London");

        System.out.println(resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 401);
    }

    @Test
    public void Test_03() {
        Response resp = given().
                param("q", "London").
                param("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
        if (resp.getStatusCode() == 200) {
            System.out.println("Api is working");
        } else {
            System.out.println("not working");
        }
    }

    @Test
    public void Test_04() {
        given().
                param("q", "London").
                param("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather").
                then().
                assertThat().statusCode(200);
    }

    @Test
    public void Test_05() {
        Response resp = given().
                param("id", "2172797").
                param("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        Assert.assertEquals(resp.statusCode(), 200);

        System.out.println(resp.asString());

    }

    @Test
    public void Test_06() {
        Response resp = given().
                param("zip", "94040,us").
                param("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        Assert.assertEquals(resp.statusCode(), 200);

        System.out.println(resp.asString());
    }

    @Test
    public void Test_07() {
        String weatherReport = given().
                parameter("id", "2172797").
                param("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather").
                then().
                contentType(ContentType.JSON).
                extract().
                path("weather[0].description");

        System.out.println("weather report " + weatherReport);
    }

    @Test
    public void Test_08() {
        Response response = given().
                parameter("id", "2172797").
                param("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        String weatherReport = response.
                then().
                contentType(ContentType.JSON).
                extract().
                path("weather[0].description");

        String expectedWeatherReport = null;

        if (weatherReport.equalsIgnoreCase(expectedWeatherReport)) {
            System.out.println("Testcase pass");
        } else {
            System.out.println("Testcase fail");
        }

        System.out.println("weather report " + weatherReport);
    }

    @Test
    public void Test_09() {
        Response response = given().
                param("id", "2172797").
                param("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        String reportbyID = response.
                then().
                contentType(ContentType.JSON).
                extract().
                path("weather[0].description");

        System.out.println("weather description " + reportbyID);

        String lon = String.valueOf(response.
                then().
                contentType(ContentType.JSON).
                extract().
                path("coord.lon"));

        System.out.println("longitude is " + lon);
    }
}
