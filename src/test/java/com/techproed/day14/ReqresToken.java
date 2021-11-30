package com.techproed.day14;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ReqresToken {



    public String getToken(){

        String url="https://reqres.in/api/login";

        HashMap<String,String> requestBody=new HashMap<>();
        requestBody.put("email","eve.holt@reqres.in");
        requestBody.put("password","cityslicka");

        Response response=given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                post(url);

        JsonPath jsonPath=response.jsonPath();

        String token=jsonPath.getString("token");

        return token;

    }


}
