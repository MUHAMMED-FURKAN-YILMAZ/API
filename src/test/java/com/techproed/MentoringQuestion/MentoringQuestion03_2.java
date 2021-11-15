package com.techproed.MentoringQuestion;

import com.techproed.testBase.JsonPlaceHolderBaseUri;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class MentoringQuestion03_2 extends JsonPlaceHolderBaseUri {
    @Test
    public void test() {

            spec01.pathParams("first", "todos");
            Response response = given().spec(spec01).when().get("/{first}");
            response.prettyPrint();

            //			 HTTP Status code should be "200"
            //			 And Content type should be in "JSON" format
            //           And 111, 121, and 131 should be among the "id"s
            response.
                    then().
                    assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("id", hasItems(111, 121, 131));

            JsonPath json = response.jsonPath();

            //			 And there should be 200 "title"
            //			 And "dignissimos quo nobis earum saepe" should be one of the "title"s

            List<String> titleList = json.getList("title");

            Assert.assertEquals(200, titleList.size());
            Assert.assertTrue(titleList.stream().anyMatch(t -> t.equals("dignissimos quo nobis earum saepe")));

            //           And 4th title is "et porro tempora"
            // 1. Way:

            String fourtTitle = json.get("find{it.id==4}.title");
            Assert.assertEquals("et porro tempora", fourtTitle);

            // 2. Way:

            Assert.assertEquals("et porro tempora", titleList.get(3));


            //			 And last title is "ipsam aperiam voluptates qui"
            // 1. Way:

            String lastTitle = json.get("find{it.id==200}.title");
            Assert.assertEquals("ipsam aperiam voluptates qui", lastTitle);

            // 2. Way:

            Assert.assertEquals("ipsam aperiam voluptates qui", titleList.get(titleList.size() - 1));
    }
}

