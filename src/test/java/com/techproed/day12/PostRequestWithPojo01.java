package com.techproed.day12;

import com.techproed.pojos.TodosPojo;
import com.techproed.testBase.JsonPlaceHolderBaseUri;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequestWithPojo01 extends JsonPlaceHolderBaseUri {

/*
    https://jsonplaceholder.typicode.com/todos url ‘ine bir request gönderildiğinde
     Request body {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
    Status kodun 201, response body ‘nin ise
     {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    } */

    @Test
    public void test(){
        spec01.pathParam("first","todos");

        TodosPojo requestExpected=new TodosPojo(21,201,"Tidy your room",false);
        System.out.println(requestExpected);

        Response response=given().contentType(ContentType.JSON).
                spec(spec01).auth().basic("admin","password123").
                body(requestExpected).when().post("/{first}");

        response.prettyPrint();

        // de-serialization
        TodosPojo actualData=response.as(TodosPojo.class);

        assertEquals(201,response.getStatusCode());
        assertEquals(requestExpected.getUserId(),actualData.getUserId());
        assertEquals(requestExpected.getId(),actualData.getId());
        assertEquals(requestExpected.getTitle(),actualData.getTitle());
        assertEquals(requestExpected.isCompleted(),actualData.isCompleted());

    }



}
