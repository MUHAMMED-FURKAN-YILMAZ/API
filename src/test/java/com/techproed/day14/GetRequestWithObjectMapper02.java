package com.techproed.day14;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestWithObjectMapper02 extends HerokuAppTestBase {

    /*
    GetRequestWithObjectMapper02:
 https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
status kodun 200 ve response body’nin
{
"firstname": "Mark",
"lastname": "Wilson",
"totalprice": 284,
"depositpaid": false,
"bookingdates": {
"checkin": "2016-08-10",
"checkout": "2018-06-22"
}
}
Olduğunu Object Mapper kullanarak test edin
     */

    @Test
    public void test(){

        spec02.pathParams("first","booking","second",2);


        String jsonData="{\n" +
                " \"firstname\": \"Susan\",\n" +
                " \"lastname\": \"Smith\",\n" +
                " \"totalprice\": 401,\n" +
                " \"depositpaid\": true,\n" +
                " \"bookingdates\": {\n" +
                "\"checkin\": \"2015-12-16\",\n" +
                "\"checkout\": \"2017-03-17\"\n" +
                " }\n" +
                " }";

        HashMap<String,Object> expectedData= JsonUtil.convertJsonToJava(jsonData,HashMap.class);

        System.out.println("expectedData = " + expectedData);

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec02).
                when().
                get("/{first}/{second}");

        response.prettyPrint();

        HashMap<String,Object> actualData=JsonUtil.convertJsonToJava(response.asString(),HashMap.class);

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),
                ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),
                ((Map)actualData.get("bookingdates")).get("checkout"));


    }

}
