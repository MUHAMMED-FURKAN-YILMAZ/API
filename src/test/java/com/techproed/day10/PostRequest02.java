package com.techproed.day10;

import com.google.gson.Gson;
import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuAppTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest02 extends HerokuAppTestBase {

//https://restful-booker.herokuapp.com/booking url ine, Request Body olarak

//{ "firstname": "Selim",
//               "lastname": "Ak",
//               "totalprice": 11111,
//               "depositpaid": false,
//               "bookingdates": {
//                   "checkin": "2020-09-09",
//                   "checkout": "2020-09-21"
//                }

// }gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,

// "booking": {
//         "firstname": " Selim ",
//         "lastname": " Ak ",
//         "totalprice":  11111,
//         "depositpaid": true,
//         "bookingdates": {
//             "checkin": "2020-09-01",
//              "checkout": " 2020-09-21”
//         },
//        }

//olduğunu test edin

    @Test
    public void test(){
        //url
        spec02.pathParam("parametre1","booking");

        //requestBody   ve expected Data aynı olduğu için tek bir JSONObject kullanılması yeterlidir.
        HerokuAppTestData herokuAppTestData=new HerokuAppTestData() ;
        JSONObject expectedRequestData=herokuAppTestData.setUpTestAndRequestData();
        System.out.println(expectedRequestData);

        //request gönder
        Response response=given().
                contentType(ContentType.JSON).
                spec(spec02).
                auth().basic("admin","password123").
                body(expectedRequestData.toString()).
                when().
                post("/{parametre1}");
       // response.prettyPrint();

        //de serialization yontemi
        HashMap<String ,Object> actualDataMap=response.as(HashMap.class);

        System.out.println(actualDataMap);

        assertEquals(expectedRequestData.getString("firstname"),
                ((Map)actualDataMap.get("booking")).get("firstname"));

        assertEquals(expectedRequestData.getString("lastname"),
                ((Map)actualDataMap.get("booking")).get("lastname"));

        assertEquals(expectedRequestData.getInt("totalprice"),
                ((Map)actualDataMap.get("booking")).get("totalprice"));

        assertEquals(expectedRequestData.getBoolean("depositpaid"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("depositpaid"));

        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                ((Map)((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));

        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                ((Map)((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));


        //JsonPath yontemi

        JsonPath jsonPath=response.jsonPath();

        assertEquals(expectedRequestData.getString("firstname"),
                jsonPath.get("booking.firstname"));

        assertEquals(expectedRequestData.getString("lastname"),
                jsonPath.get("booking.lastname"));

        assertEquals(expectedRequestData.get("totalprice"),
                jsonPath.getInt("booking.totalprice"));

        assertEquals(expectedRequestData.getBoolean("depositpaid"),
                jsonPath.getBoolean("booking.depositpaid"));

        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                jsonPath.getString("booking.bookingdates.checkin"));

        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                jsonPath.getString("booking.bookingdates.checkout"));


    }


}
