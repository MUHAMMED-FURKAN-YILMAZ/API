package com.techproed.day04;


import io.restassured.internal.http.HttpResponseDecorator;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GetRequest02_tryCatch {

    /*
       https://restful-booker.herokuapp.com/booking/1001 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 404 oldugunu
    ve Response body'sinin "Not Found" icerdigini
    ve Response body'sinin "API" icermedigini test edin
     */
    @Test
    public void test02(){
        String url1="https://restful-booker.herokuapp.com/booking/1001";

        try {
            Response  response=given().accept("application/json").when().get(url1);

        }catch (Throwable e){
            HttpResponseDecorator response=  ((HttpResponseException)e).getResponse();

            assertTrue(response.getStatus() == 404);
            System.out.println("response.toString() = " + response.toString());
            System.out.println("response.getAllHeaders() = " + response.getAllHeaders());
            System.out.println("response.getParams() = " + response.getParams());
            System.out.println("response.getContentType() = " + response.getContentType());
            assertTrue(response.toString().contains("Not Found"));
            assertFalse(response.getData().toString().contains("API"));

        }

    }
}