package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {

    //https://restful-booker.herokuapp.com/booking/3 adresine bir request
    //  gonderildiginde donecek cevap(response) icin

    //HTTP status kodunun 200
    //Content Type'in Json
    //Ve Status Line'in HTTP/1.1 200 OK
    //Oldugunu test edin

    @Test
    public void  test() {
        //1- Api testi yaparken ilk olarak url (endpoint) belirlenmeli
        String url = "https://restful-booker.herokuapp.com/booking/3";

        //2- Beklenen sonuc nedir(expected result) olusturulur
            //--bu case'de benden body dogrulamasi istenmedigi icin simdilik beklenen sonuc olusturmuyoruz

        //3- Request gonder
        Response response= given().
                accept(ContentType.JSON).//accept("application/json") kullanilanilir.
                when().
                get(url);

        response.prettyPrint();

        // 4- Actual result olustur
            //--response body ile ilgili islem yapmayacagimiz icin simdi olusturmayacagiz

        // 5- Assertion(dogrulama) yap
        System.out.println("Status code : "+response.getStatusCode());//response'dan gelen status code'u verir
        System.out.println("Content Type : "+response.getContentType());//response'dan gelen content Type'i verir
        System.out.println("Status line : "+response.getStatusLine());//response'dan gelen status line'i verir
        System.out.println("response.getHeaders() = " + response.getHeaders());

        /*
        Assert.assertEquals(200,response.getStatusCode());

        Assert.assertEquals("application/json; charset=utf-8",response.getContentType());

        Assert.assertEquals("HTTP/1.1 200 OK",response.getStatusLine());
        */
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

    }

}
