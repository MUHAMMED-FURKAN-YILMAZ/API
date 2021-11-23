package com.techproed.day11;

import com.techproed.testBase.JsonPlaceHolderBaseUri;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PostRequest03 extends JsonPlaceHolderBaseUri {

    //https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,

    //     {
    //     "userId": 55,
    //     "title": "Tidy your room",
    //     "completed": false
    //   }


    //Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin

    //   {
    //     "userId": 55,
    //     "title": "Tidy your room",
    //     "completed": false,
    //     "id": …
    //    }

    @Test
    public void test(){
        spec01.pathParam("first","todos");

        JsonPlaceHolderTestData jsonPlaceHolderTestData=new JsonPlaceHolderTestData();
        JSONObject expectedRequestData=jsonPlaceHolderTestData.setUpPostData();
        System.out.println("*****"+expectedRequestData);

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin","password123").
                body(expectedRequestData.toString()).
                when().
                post("/{first}");

        response.prettyPrint();

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);

        System.out.println("*********"+ actualDataMap);

        // de serialization
        assertEquals(expectedRequestData.getInt("userId"),
                actualDataMap.get("userId"));

        assertEquals(expectedRequestData.getString("title"),
                actualDataMap.get("title"));

        assertEquals(expectedRequestData.getBoolean("completed"),
                actualDataMap.get("completed"));


        // Matchers class
        response.then().assertThat().
                statusCode(expectedRequestData.getInt("statuscode")).
                body("completed",equalTo(expectedRequestData.getBoolean("completed")),
                        "title",equalTo(expectedRequestData.getString("title")),
                        "userId",equalTo(expectedRequestData.getInt("userId")));


        // JsonPath yontemi
        JsonPath jsonPath=response.jsonPath();

        assertEquals(expectedRequestData.get("statuscode"), response.getStatusCode());

        assertEquals(expectedRequestData.getInt("userId"),
                jsonPath.getInt("userId"));

        assertEquals(expectedRequestData.getString("title"),
                jsonPath.getString("title"));

        assertEquals(expectedRequestData.getBoolean("completed"),
                jsonPath.getBoolean("completed"));
    }


}
