package com.techproed.day12;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class DeleteRequest01 extends DummyTestBase {

    /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
}
     */

    @Test
    public void test(){
        spec03.pathParams("first","delete","second",2);

        DummyTestData dummyTestData=new DummyTestData();
        JSONObject expectedData=dummyTestData.setUpDeleteExpectedData();

// deleteRequest getRequest gibi body gerekmez
        Response response=given().contentType(ContentType.JSON).
                spec(spec03).auth().basic("admin","password123")
                .when().delete("/{first}/{second}");

        response.prettyPrint();

        // Matchers class

        response.then().assertThat().statusCode(200).
                body("status",equalTo(expectedData.getString("status")),
                        "data",equalTo(expectedData.getString("data")),
                        "message",equalTo(expectedData.getString("message")));


    }

}
