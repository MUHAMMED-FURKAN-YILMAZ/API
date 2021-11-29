package com.techproed.day13;

import com.google.gson.Gson;
import com.techproed.pojos.dummyPojo.Data;
import com.techproed.pojos.dummyPojo.DummyPojo;
import com.techproed.testBase.DummyTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestWithPojo01 extends DummyTestBase {
    /*
    http://dummy.restapiexample.com/api/v1/employee/1 url ‘ine bir get request gönderildiğinde , dönen response ‘un,
                           Status kodunun 200 ve response body’nin
                                {
                                 "status": "success",
                                 "data": {
                                     "id": 1,
                                     "employee_name": "Tiger Nixon",
                                     "employee_salary": 320800,
                                     "employee_age": 61,
                                     "profile_image": ""
                                 },
                                 "message": "Successfully! Record has been fetched."
                                }
Olduğunu test edin
     */

    @Test
    public void test(){

        spec03.pathParams("first","employee","second",1);

        Data data=new Data(1,"Tiger Nixon",320800,61,"");
        DummyPojo expectedData=new DummyPojo("success",data,"Successfully! Record has been fetched.");

        Response response=given().contentType(ContentType.JSON).
                spec(spec03).when().get("/{first}/{second}");

        response.prettyPrint();

        DummyPojo actualData=response.as(DummyPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getData().getId(),actualData.getData().getId());
        assertEquals(expectedData.getData().getemployee_name(),actualData.getData().getemployee_name());
        assertEquals(expectedData.getData().getemployee_name(),actualData.getData().getemployee_name());
        assertEquals(expectedData.getData().getemployee_age(),actualData.getData().getemployee_age());
        assertEquals(expectedData.getData().getemployee_salary(),actualData.getData().getemployee_salary());
        assertEquals(expectedData.getData().getprofile_image(),actualData.getData().getprofile_image());
        assertEquals(expectedData.getMessage(),actualData.getMessage());

    //------------------------------ soru burada bitti --------------------------

  // Serialization java yapisinda olan datalari json formatina donusturme islemidir
  // Gson class'indan bir obje uretilir

        Gson gson=new Gson();
        String jsonFromJava=gson.toJson(actualData);
        System.out.println("jsonFromJava = " + jsonFromJava);







    }

}
