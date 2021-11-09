package com.techproed.day05;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestMatcher04 {
    //https://restful-booker.herokuapp.com/booking/5 url'ine
    //accept type'i "application/json" olan GET request'i yolladigimda
    //gelen response'un
    //status kodunun 200
    //ve content type'inin "application/json"
    //ve firstname'in “Mary"
    //ve totalprice’in 662
    //ve checkin date'in 2020-04-10"oldugunu test edin
    @Test
    public void test(){

        String url = "https://restful-booker.herokuapp.com/booking/5 url'ine";
        Response response= given().
                accept("application/json").
                when().get(url);

        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType("application/json").
        body("firstname",equalTo("Mary"),
                "totalprice",equalTo(662),
                "bookingdates.checkin",equalTo("2020-04-10"));


    }
}
