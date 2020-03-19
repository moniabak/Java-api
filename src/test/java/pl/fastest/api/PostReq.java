import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class PostReq {
    @Test
    public void getUserDetailsTest() {
        Response resp = given().
                when().
                get("https://my-json-server.typicode.com/moniabak/json-server/posts");

        System.out.println("get " + resp.asString());
    }

    @Test
    public void postUserDetailsTest() {
        Response resp = given().
                body("{ \"title\": \"json-server\", \"author\": \"typicode\" }").
                when().
                contentType(ContentType.JSON).
                post("https://my-json-server.typicode.com/moniabak/json-server/posts");

        Assert.assertEquals(resp.statusCode(), 201);
    }


}
