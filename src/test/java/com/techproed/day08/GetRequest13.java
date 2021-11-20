package com.techproed.day08;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.response.Response;

import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetRequest13 extends DummyTestBase {

    //http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    //Status kodun 200 olduğunu,
    //5. Çalışan isminin "Airi Satou" olduğunu ,
    // çalışan sayısının 24 olduğunu,
    //Sondan 2. çalışanın maaşının 106450 olduğunu
    //40,21 ve 19 yaslarında çalışanlar olup olmadığını

    //11. Çalışan bilgilerinin
    //  {
    // “id”:”11”
         // "employee_name": "Jena Gaines",
         //"employee_salary": "90560",
         //"employee_age": "30",
         //"profile_image": "" }
    //}
    // gibi olduğunu test edin.

    @Test
    public void test(){

        spec03.pathParam("first","employees");


        DummyTestData dummyTestData= new DummyTestData();
        HashMap<String,Object> expectedDataMap= dummyTestData.setupTestData();
        System.out.println(expectedDataMap);


        Response response=given().
                accept("application/json").
                spec(spec03).when().
                get("/{first}");
        // response.prettyPrint();


        HashMap<String ,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);

                //Status kodun 200 olduğunu
        assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());

                //5. Çalışan isminin "Airi Satou" olduğunu
        assertEquals(expectedDataMap.get("besinciCalisan"),
                ((Map)((List)actualDataMap.get("data")).get(4)).get("employee_name"));

                // çalışan sayısının 24 olduğunu,
        assertEquals(expectedDataMap.get("calisanSayisi"),
                ((List)actualDataMap.get("data")).size());

                //Sondan 2. çalışanın maaşının 106450 olduğunu
        // once actual datadan bize donen listin size'ini almaliyiz
        int dataSize=((List) actualDataMap.get("data")).size();
        assertEquals(expectedDataMap.get("sondanIkinciCalisanMaasi"),
                ((Map)((List)actualDataMap.get("data")).get(dataSize-2)).get("employee_salary"));

                //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        List<Integer> actualYasListesi= new ArrayList<>();
        for (int i = 0; i <dataSize ; i++) {
            actualYasListesi.add(   (Integer) ((Map)((List<?>) actualDataMap.get("data")).get(i)).get("employee_age")   );
        }

        assertTrue(actualYasListesi.containsAll((List)expectedDataMap.get("arananYaslari")));


        //11. Çalışan bilgilerinin
        //  {
        // “id”:”11”
             // "employee_name": "Jena Gaines",
             //"employee_salary": "90560",
             //"employee_age": "30",
             //"profile_image": "" }
        //}
        assertEquals(((Map)expectedDataMap.get("onBirinciCalisan")).get("employee_name"),
                ((Map)((List<?>) actualDataMap.get("data")).get(10)).get("employee_name"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onBirinciCalisan")).get("employee_salary"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary")   );

        assertEquals(((Map<?, ?>) expectedDataMap.get("onBirinciCalisan")).get("employee_age"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age")  );

        assertEquals(((Map<?, ?>) expectedDataMap.get("onBirinciCalisan")).get("profile_image"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("profile_image")  );


    }

}
