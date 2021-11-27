package com.techproed.day12;

import com.techproed.testBase.JsonPlaceHolderBaseUri;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class PatchRequest01 extends JsonPlaceHolderBaseUri {
    //https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
    //   {
    //
    //      "title": "API calismaliyim"
    //
    //     }
    //Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    //{
    // "userId": 10,
    // "title": "API calismaliyim"
    // "completed": true,
    // "id": 198
    //}

    @Test
    public void test(){

        spec01.pathParams("first","todos","second",198);

        //ecpected ve request data olustur.
        JsonPlaceHolderTestData jsonPlaceHolderTestData=new JsonPlaceHolderTestData();

        JSONObject requestData=jsonPlaceHolderTestData.setUpPatchRequestData();
        JSONObject expectedData=jsonPlaceHolderTestData.setUpPatchExpectedData();

        Response response=given().
                contentType(ContentType.JSON).spec(spec01)
                .auth().basic("admin","password123").
                body(requestData.toString()).when().patch("/{first}/{second}");

        response.prettyPrint();

        // De-serialization yontemi

        HashMap<String,Object> actualData=response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getInt("userId"),actualData.get("userId"));
        assertEquals(expectedData.getInt("id"),actualData.get("id"));
        assertEquals(expectedData.getString("title"),actualData.get("title"));
        assertEquals(expectedData.getBoolean("completed"),actualData.get("completed"));

    }




}
