package com.techproed.testData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {

    /*
        http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    5. Çalışan isminin "Airi Satou" olduğunu ,
    çalışan sayısının 24 olduğunu,
   Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını

    11. Çalışan bilgilerinin
      {
     "id": 11,
            "employee_name": "Jena Gaines",
            "employee_salary": 90560,
            "employee_age": 30,
            "profile_image": ""
    }
    gibi olduğunu test edin.
     */

    public HashMap<String, Object> setupTestData(){

        List<Integer> yaslar= Arrays.asList(40, 21, 19);

        HashMap<String ,Object> onBirinci=new HashMap<>();
        onBirinci.put("id",11);
        onBirinci.put("employee_name","Jena Gaines");
        onBirinci.put("employee_salary",90560);
        onBirinci.put("employee_age",30);
        onBirinci.put("profile_image","");

        HashMap<String ,Object> expectedData=new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("besinciCalisan","Airi Satou");
        expectedData.put("calisanSayisi",24);
        expectedData.put("sondanIkinciCalisanMaasi",106450);
        expectedData.put("arananYaslari",yaslar);
        expectedData.put("onBirinciCalisan",onBirinci);

        return expectedData;


    }

}
