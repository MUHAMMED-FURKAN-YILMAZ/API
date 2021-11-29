package com.techproed.day08;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuAppTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12_Deserialization extends HerokuAppTestBase {
    //https://restful-booker.herokuapp.com/booking/1 url ine
    //bir istek gönderildiğinde
    // dönen response body nin
    //  {
    //   "firstname": "Eric",
    //   "lastname": "Smith",
    //   "totalprice": 555,
    //   "depositpaid": false,
    //   "bookingdates": {
    //       "checkin": "2016-09-09",
    //       "checkout": "2017-09-21"
    //    }
    //} gibi olduğunu test edin

    @Test
    public void test(){
        //set the URL
        spec02.pathParams("first","booking","second",1);


       // 2.Step: Set the expected data
        HerokuAppTestData herokuAppTestData=new HerokuAppTestData();
        HashMap<String,Object> expectedTestData= (HashMap<String, Object>) herokuAppTestData.setupTestData();

        System.out.println(expectedTestData);


        //3.Step: Send the request and get the response
        Response response=given().
                accept("application/json").
                spec(spec02).when().
                get("/{first}/{second}");

        // 4.Step: Get the actual data
        HashMap<String,Object> actualTestData=response.as(HashMap.class);

        System.out.println(actualTestData);


        //  5.Step: Do the assertion

        // 1. way deserialization
        Assert.assertEquals(expectedTestData.get("firstname"),actualTestData.get("firstname"));
        Assert.assertEquals(expectedTestData.get("lastname"),actualTestData.get("lastname"));
        Assert.assertEquals(expectedTestData.get("totalprice"),actualTestData.get("totalprice"));
        Assert.assertEquals(expectedTestData.get("depositpaid"),actualTestData.get("depositpaid"));
        Assert.assertEquals(expectedTestData.get("bookingdates"),actualTestData.get("bookingdates"));

        Assert.assertEquals(((Map)expectedTestData.get("bookingdates")).get("checkin"),
                ((Map)actualTestData.get("bookingdates")).get("checkin"));

        Assert.assertEquals(   ((Map) expectedTestData.get("bookingdates")).get("checkout"),
        ((Map) actualTestData.get("bookingdates")).get("checkout"));

        //data kisaysa bu sekilde map-map karsilastirabiliriz
        //Assert.assertEquals(expectedTestData.get("bookingdates"),actualTestData.get("bookingdates"));

        //2. way JsonPath
        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedTestData.get("firstname"),jsonPath.getString("firstname"));
        Assert.assertEquals(expectedTestData.get("lastname"),jsonPath.getString("lastname"));
        Assert.assertEquals(expectedTestData.get("totalprice"),jsonPath.getInt("totalprice"));
        Assert.assertEquals(expectedTestData.get("depositpaid"),jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals(((Map)expectedTestData.get("bookingdates")).get("checkin"),
                jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals(((Map)expectedTestData.get("bookingdates")).get("checkin"),
                jsonPath.getString("bookingdates.checkin"));
    }

}
