package com.techproed.MentoringQuestion;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class MentoringQuestion02 {
    /*
        https://jsonplaceholder.typicode.com/posts/44 url'ine bir GET request yolladigimizda
        donen Response'in
        status code'unun 200,
        ve content type'inin Aplication.JSON,
        		ve response body'sinde bulunan userId'nin 5,
        		ve response body'sinde bulunan title'in "optio dolor molestias sit"
        			oldugunu test edin.
       */
   @Test
    public void test(){
       String url="https://jsonplaceholder.typicode.com/posts/44";

       Response response= given().
               accept("application/json").
               when().
               get(url);


       response.then().assertThat().
               statusCode(200).
               contentType("application/json").
               body("userId",Matchers.equalTo(5),
               "title",Matchers.equalTo("optio dolor molestias sit"));

        /* 2.YOL json path ile body icini dogrulama
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(5,jsonPath.getInt("userId"));
        Assert.assertEquals("optio dolor molestias sit",jsonPath.getString("title"));
        */

   }
}
