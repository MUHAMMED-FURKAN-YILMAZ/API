package com.techproed.day11;


import com.techproed.testBase.JsonPlaceHolderBaseUri;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PutRequest01 extends JsonPlaceHolderBaseUri {

    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde

   {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }

Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin

{
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false,
 "id": 198
}
     */

    @Test
    public void test(){
    spec01.pathParams("first","todos","second",198);

        JsonPlaceHolderTestData jsonPlaceHolderTestData=new JsonPlaceHolderTestData();
        JSONObject expectedRequest=jsonPlaceHolderTestData.setUpPutData();
        System.out.println(expectedRequest);

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                body(expectedRequest.toString()).
                auth().basic("admin","password123").
                when().put("/{first}/{second}");

        response.prettyPrint();

        // jsonPath
        JsonPath jsonPath=response.jsonPath();

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedRequest.getInt("userId"),jsonPath.getInt("userId"));
        assertEquals(expectedRequest.getString("title"),jsonPath.getString("title"));
        assertEquals(expectedRequest.getBoolean("completed"),jsonPath.getBoolean("completed"));



    }


}
