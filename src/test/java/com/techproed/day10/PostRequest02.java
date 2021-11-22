package com.techproed.day10;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuAppTestData;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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

        spec02.pathParam("first","booking");

        HerokuAppTestData herokuAppTestData=new HerokuAppTestData();
        JSONObject expectedAndRequestData= herokuAppTestData.setUpTestAndRequestData();

        System.out.println(expectedAndRequestData.toString());

        Response response=
                given().accept("application/json").spec(spec02).
                        body(expectedAndRequestData).post("/{first}");


    }


}
