package com.techproed.day07;

import com.techproed.testBase.JsonPlaceHolderBaseUri;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest11 extends JsonPlaceHolderBaseUri {
    //https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
    // Dönen response un
    // Status kodunun 200, dönen body de,
    //       "completed": değerinin false
    //       "title”: değerinin “quis ut nam facilis et officia qui”
    //       "userId" sinin 1 ve header değerlerinden
    // "Via" değerinin “1.1 vegur” ve
    //       "Server" değerinin “cloudflare” olduğunu test edin…
    /*
    url olustur
    --expected data
    request gonder
    --actual data
    assertion
     */

    @Test
    public void test(){

        // response ile -> body disindakileri
        // jsonPath ile -> body icini;   assert etmek saha iyidir
        spec01.pathParams("parametre1","todos","parametre2",2);

        HashMap<String,Object> expectedData=new HashMap<>();

        expectedData.put("statuscode",200);
        expectedData.put("via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);

        System.out.println(expectedData);

        Response response=given().
                accept("application/json").
                spec(spec01).when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();


        //*** 1. yontem -->Matchers class ile assertion islemi yaptik...***

        response.then().assertThat().
                statusCode((Integer) expectedData.get("statuscode")).
                headers("via",equalTo(expectedData.get("via")),
                        "Server",equalTo(expectedData.get("Server"))).
                body("userId",equalTo(expectedData.get("userId")),
                        "title",equalTo(expectedData.get("title")),
                        "completed",equalTo(expectedData.get("completed")));


        // **** 2. yontem *******

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedData.get("statuscode"),response.statusCode());
        Assert.assertEquals(expectedData.get("via"),response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),jsonPath.getBoolean("completed"));


        // **** 3. yontem-- deserialization *******
            // --object mapper
            // --pojo class ile birlikte map


    }

}
