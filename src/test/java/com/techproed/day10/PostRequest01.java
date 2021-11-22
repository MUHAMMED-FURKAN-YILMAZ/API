package com.techproed.day10;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends DummyTestBase {

    //http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
    //{
    //    "name":"Muhammed Furkan",
    //           "salary":"15000",
    //           "age":"24"
    //}
    //gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
    //{
    //   "status": "success",
    //           "data": {
    //        “id”:…
    //   },
    //   "message": "Successfully! Record has been added."
    //}
    //olduğunu test edin

    @Test
    public void test(){

        spec03.pathParam("first","create");

        DummyTestData dummyTestData=new DummyTestData();

        // post request yaparken biz body gondermek zorundayiz, testData class'inda olusturugumuz
        // request body'i burada cagiriyoruz
        HashMap<String,String> requestBodyMap= dummyTestData.setupRequestBody();
        HashMap<String,Object> expectedDataMap=dummyTestData.setupExpectedData();

        Response response=given().accept("application/json").auth().basic("admin","password123")
        .spec(spec03).body(requestBodyMap).
                when().post("/{first}");

        response.prettyPrint();

        // de serialization

        HashMap<String ,Object> actualDataMap=response.as(HashMap.class);

        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));


        // JsonPath ile
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("status"),jsonPath.getString("status"));
        Assert.assertEquals(expectedDataMap.get("message"),jsonPath.getString("message"));

    }



}
