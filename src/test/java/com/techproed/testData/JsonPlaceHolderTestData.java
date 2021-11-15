package com.techproed.testData;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String,Object> setupTestData(){
        HashMap<String,Object> expectedData=new HashMap<>();

        expectedData.put("statuscode",200);
        expectedData.put("via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);

        return expectedData;
    }
}
