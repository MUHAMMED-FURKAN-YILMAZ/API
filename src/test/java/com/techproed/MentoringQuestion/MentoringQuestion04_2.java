package com.techproed.MentoringQuestion;

import com.techproed.testBase.DummyTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class MentoringQuestion04_2 extends DummyTestBase {

    //http://dummy.restapiexample.com/api/v1/employee/3 url'ine bir GET request gonderdigimizde
    //donen response'un asagidaki gibi oldugunu test edin.
    //    Response Body
    //    {
    //        "status":"success",
    //        "data":{
    //                "id":3,
    //                "employee_name":"Ashton Cox",
    //                "employee_salary":86000,
    //                "employee_age":66,
    //                "66":""
    //                },
    //        "message":"Successfully! Record has been fetched."
    //    }

    @Test
    public  void  test(){


            spec03.pathParams("first", "employee", "second", 3);
            Response response = given().spec(spec03).when().get("/{first}/{second}");

        Map<String, Object> dataMap = new HashMap<>(); // (Inner data Map)

        dataMap.put("id",3);
        dataMap.put("employee_name","Ashton Cox");
        dataMap.put("employee_salary",86000);
        dataMap.put("employee_age",66);
        dataMap.put("profile_image","");

        Map <String,Object> expectedBodyMap = new HashMap<>(); // (Expected body)

        expectedBodyMap.put("status","success");
        expectedBodyMap.put("message","Successfully! Record has been fetched.");
        expectedBodyMap.put("data",dataMap);

        System.out.println(expectedBodyMap);

        //3.Step: Send the request and get the response


        response.prettyPrint();

        //4.Step: Do the assertion

        //1.Way:

        response.then().assertThat().statusCode(200).
                body("status",equalTo(expectedBodyMap.get("status")),
                        "message",equalTo(expectedBodyMap.get("message")),
                        "data.id",equalTo(dataMap.get("id")),
                        "data.employee_name",equalTo(dataMap.get("employee_name")),
                        "data.employee_salary",equalTo(dataMap.get("employee_salary")),
                        "data.employee_age",equalTo(dataMap.get("employee_age")),
                        "data.profile_image",equalTo(dataMap.get("profile_image")));

        //2.Way: De-serialization (GSON)

        Map <String,Object> actualBodyMap = response.as(HashMap.class);
        System.out.println(actualBodyMap);

        assertEquals(expectedBodyMap.get("status"),actualBodyMap.get("status"));
        assertEquals(expectedBodyMap.get("message"),actualBodyMap.get("message"));
        assertEquals(dataMap.get("id"), ((Map)actualBodyMap.get("data")).get("id"));
        assertEquals(dataMap.get("employee_name"), ((Map)actualBodyMap.get("data")).get("employee_name"));
        assertEquals(dataMap.get("employee_salary"), ((Map)actualBodyMap.get("data")).get("employee_salary"));
        assertEquals(dataMap.get("employee_age"), ((Map)actualBodyMap.get("data")).get("employee_age"));
        assertEquals(dataMap.get("profile_image"), ((Map)actualBodyMap.get("data")).get("profile_image"));


    }
}
