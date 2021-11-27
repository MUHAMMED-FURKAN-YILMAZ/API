package com.techproed.testData;

import org.json.JSONObject;

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


    public HashMap<String, Integer> setupTestData2(){

        //http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
        //Status kodun 200 olduğunu,
        //En yüksek maaşın 725000 olduğunu,
        //En küçük yaşın 19 olduğunu,
        //İkinci en yüksek maaşın 675000
        //olduğunu test edin.

        HashMap<String ,Integer> expectedData=new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("enYuksekMaas",725000);
        expectedData.put("enKucukYas",19);
        expectedData.put("ikinciEnYuksekMaas",675000);


        return expectedData;
    }




    public HashMap<String,String> setupRequestBody(){

            //    "name":"Muhammed Furkan",
            //    "salary":"15000",
            //    "age":"24"
            //}

        HashMap<String,String> requestBody= new HashMap<>();

        requestBody.put("name","Muhammed Furkan");
        requestBody.put("salary","15000");
        requestBody.put("age","24");

        return requestBody;

    }

    public HashMap<String, Object> setupExpectedData(){

        HashMap<String,Object> expectedData= new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put( "status", "success");
        expectedData.put("message", "Successfully! Record has been added.");

        return expectedData;
    }


    /*
    {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
}
     */
public JSONObject setUpDeleteExpectedData(){
    JSONObject ExpectedData=new JSONObject();

    ExpectedData.put("status","success");
    ExpectedData.put("data","2");
    ExpectedData.put("message","Successfully! Record has been deleted");

    return ExpectedData;
}


}
