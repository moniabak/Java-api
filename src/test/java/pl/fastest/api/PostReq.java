package pl.fastest.api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.fastest.advanced.InfoAr;
import pl.fastest.advanced.PostsAr;
import pl.fastest.classes.Info;
import pl.fastest.classes.Posts;
import pl.fastest.classes._Posts;

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

        System.out.println("resp " + resp.statusCode());
        Assert.assertEquals(resp.statusCode(), 201);
    }

    @Test
    public void postTest() {
        Posts posts = new Posts();
//        posts.setId("3");
        posts.setTitle("posts by object");
//        posts.setAuthor("Yoda 1");

        Response response = given().
                when().
                contentType(ContentType.JSON).
                body(posts).
                post("https://my-json-server.typicode.com/moniabak/json-server/posts");

        System.out.println("code : " + response.asString());

        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test
    public void putTest() {
        Posts posts = new Posts();
        posts.setId("1");
        posts.setTitle("posts req by object");
//        posts.setAuthor("Yoda Komoda");

        Response response = given().
                when().
                contentType(ContentType.JSON).
                body(posts).
                put("https://my-json-server.typicode.com/moniabak/json-server/posts/1");

        System.out.println("code : " + response.asString());

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void patchTest() {
        Response response = given().
                when().
                contentType(ContentType.JSON).
                body("{\"title\" : \"asfdsfadf\"}").
                patch("https://my-json-server.typicode.com/moniabak/json-server/posts/1");

        System.out.println("code : " + response.asString());

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void deleteTest() {
        Response response = given().
                when().
                contentType(ContentType.JSON).
                delete("https://my-json-server.typicode.com/moniabak/json-server/posts/1");

        System.out.println("code : " + response.asString());

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void complexPostTest() {
        Info info = new Info();
        info.setEmail("emsg@fdsf.pl");
        info.setPhone("987987987");

        _Posts posts = new _Posts();
        posts.setId("10");
        posts.setTitle("Title bibile");
        posts.setInfo(info);

        Response response = given().
                when().
                contentType(ContentType.JSON).
                body(posts).
                post("https://my-json-server.typicode.com/moniabak/json-server/posts");

        System.out.println("code : " + response.asString());

        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test
    public void arrayPostTest() {
        InfoAr info = new InfoAr();
        info.setEmail("emsg@fdsf.pl");
        info.setPhone("987987987");

        InfoAr info2 = new InfoAr();
        info2.setEmail("nan@fdsf.pl");
        info2.setPhone("111111");

        PostsAr posts = new PostsAr();
        posts.setId("223");
        posts.setTitle("Title bibile");
        posts.setInfoAr(new InfoAr[]{info, info2});

        Response response = given().log().body().
                when().
                contentType(ContentType.JSON).
                body(posts).
                post("https://my-json-server.typicode.com/moniabak/json-server/posts");

        System.out.println("code : " + response.asString());

        Assert.assertEquals(response.statusCode(), 201);
    }
}
