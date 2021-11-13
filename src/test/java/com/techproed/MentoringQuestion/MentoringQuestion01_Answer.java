package com.techproed.MentoringQuestion;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class MentoringQuestion01_Answer {

    @Test
    public void get01(){
        /*
        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde donen Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.
         */

        // 1 - request url ve body'sini hazirlamak

        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet

        Response response = given().when().get(url);
        response.prettyPrint();

        System.out.println("Status code : " + response.getStatusCode());  //Status code : 200
        System.out.println("Content Type : " + response.getContentType()); //Content Type : application/json; charset=utf-8
        System.out.println("Server header degeri : " + response.getHeader("Server")); //Server header degeri : Cowboy
        System.out.println("Status Line : " + response.getStatusLine()); //Status Line : HTTP/1.1 200 OK
        System.out.println("Response suresi : " + response.getTime()); //Response suresi : 2375
    }
}
