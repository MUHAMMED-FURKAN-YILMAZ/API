package com.techproed.testData;

import org.json.JSONObject;

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

    //     {
    //     "userId": 55,
    //     "title": "Tidy your room",
    //     "completed": false
    //   }
    public JSONObject setUpPostData(){

        JSONObject expectedRequest=new JSONObject();

        expectedRequest.put("statuscode",201);
        expectedRequest.put("userId",55);
        expectedRequest.put("title","Tidy your room");
        expectedRequest.put("completed",false);

        return expectedRequest;
    }

    /*
    {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }
     */
    public JSONObject setUpPutData(){

        JSONObject expectedRequest=new JSONObject();

        expectedRequest.put("userId",21);
        expectedRequest.put("title","Wash the dishes");
        expectedRequest.put("completed",false);

        return expectedRequest;
    }



        //{
        //      "title": "API calismaliyim"
        //
        //     }
    public JSONObject setUpPatchRequestData(){

        JSONObject requestData=new JSONObject();
        requestData.put("title","API calismaliyim");
        return requestData;
    }

    /*
    {
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false,
 "id": 198
} */
    public JSONObject setUpPatchExpectedData(){

        JSONObject expectedData=new JSONObject();
        expectedData.put("userId",10);
        expectedData.put("title","API calismaliyim");
        expectedData.put("completed",true);
        expectedData.put("id",198);

        return expectedData;

    }

    
}
