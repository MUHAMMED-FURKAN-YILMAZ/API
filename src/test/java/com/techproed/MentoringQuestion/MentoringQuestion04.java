package com.techproed.MentoringQuestion;

import com.techproed.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MentoringQuestion04 extends DummyTestBase {
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
    public  void  test() {
        spec03.pathParams("first", "employee", "second", 3);

        HashMap<String, Object> dataMap = new HashMap<>(); // (Inner data Map)

        dataMap.put("id",3);
        dataMap.put("employee_name","Ashton Cox");
        dataMap.put("employee_salary",86000);
        dataMap.put("employee_age",66);
        dataMap.put("profile_image","");

        HashMap <String,Object> expectedBodyMap = new HashMap<>(); // (Expected body)

        expectedBodyMap.put("status","success");
        expectedBodyMap.put("message","Successfully! Record has been fetched.");
        expectedBodyMap.put("data",dataMap);

        Response response = given().spec(spec03).when().get("/{first}/{second}");

        response.then().assertThat().body("status",equalTo(expectedBodyMap.get("status")),
                "data.id",equalTo(dataMap.get("id")),
                "data.employee_name",equalTo(dataMap.get("employee_name")),
                "data.employee_salary",equalTo(dataMap.get("employee_salary")),
                "data.employee_age",equalTo(dataMap.get("employee_age")),
                "data.profile_image",equalTo(dataMap.get("profile_image")),
                "message",equalTo(expectedBodyMap.get("message")));



    }
}
