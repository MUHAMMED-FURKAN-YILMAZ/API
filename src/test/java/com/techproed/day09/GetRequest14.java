package com.techproed.day09;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends DummyTestBase {

    //http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    //Status kodun 200 olduğunu,
    //En yüksek maaşın 725000 olduğunu,
    //En küçük yaşın 19 olduğunu,
    //İkinci en yüksek maaşın 675000
    //olduğunu test edin.

    @Test
    public void test(){
        spec03.pathParam("first","employees");

        DummyTestData dummyTestBase=new DummyTestData();
        HashMap<String,Integer> expectedDataMap=dummyTestBase.setupTestData2();
        System.out.println(expectedDataMap);

        Response response=given().accept("application/json").
                spec(spec03).when().
                get("/{first}");

     //   response.prettyPrint();

// 1- De-Serialization yontemi ile

        HashMap<Integer,Object> actualDataMap=response.as(HashMap.class);

       // System.out.println(actualDataMap);




        //Status kodun 200 olduğunu,
        Assert.assertEquals(expectedDataMap.get("statusCode"),(Integer) response.statusCode());

        //En yüksek maaşın 725000 olduğunu,
        List<Integer> maasListesi=new ArrayList<>();
        int dataSize=( (List)   actualDataMap.get("data")).size();

        for (int i = 0; i < dataSize; i++) {
        maasListesi.add( (Integer)     ((Map)((List)actualDataMap.get("data")).get(i)).get("employee_salary")   );
        }
        Collections.sort(maasListesi);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),maasListesi.get(maasListesi.size()-1));

        //İkinci en yüksek maaşın 675000
        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"),maasListesi.get(maasListesi.size()-2));

        //En küçük yaşın 19 olduğunu,
        List<Integer> yasListesi=new ArrayList<>();

        for (int i = 0; i < dataSize; i++) {
            yasListesi.add((Integer)((Map)((List)actualDataMap.get("data")).get(i)).get("employee_age"));
        }
       Collections.sort(yasListesi);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"),yasListesi.get(0));


// 2-JsonPath YOntemi ile

        JsonPath jsonPath=response.jsonPath();

        //En yüksek maaşın 725000 olduğunu,
        List<Integer> maasListesiJson=jsonPath.getList("data.employee_salary");
        Collections.sort(maasListesiJson);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),maasListesi.get(maasListesi.size()-1));

        //İkinci en yüksek maaşın 675000
        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"),maasListesi.get(maasListesi.size()-2));

        //En küçük yaşın 19 olduğunu,
        List<Integer> yasListesiJson=jsonPath.getList("data.employee_age");
        Collections.sort(yasListesiJson);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"),yasListesiJson.get(0));



    }


}
