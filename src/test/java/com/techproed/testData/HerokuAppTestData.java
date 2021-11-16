package com.techproed.testData;

import java.util.HashMap;
import java.util.Map;

public class HerokuAppTestData {

    //   "firstname": "Eric",
    //   "lastname": "Smith",
    //   "totalprice": 555,
    //   "depositpaid": false,
    //   "bookingdates": {
    //       "checkin": "2016-09-09",
    //       "checkout": "2017-09-21"
    //    }
    //}

    public Map<String,Object> setupTestData(){

        HashMap<String,Object> bookingdates=new HashMap<>();

        bookingdates.put("checkin","2016-07-28");
        bookingdates.put("checkout","2020-03-26");

        HashMap<String,Object> expectedData=new HashMap<>();

        expectedData.put("firstname","Sally");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",716);
        expectedData.put("depositpaid",false);
        expectedData.put("bookingdates",bookingdates);

        return expectedData;
    }

}
