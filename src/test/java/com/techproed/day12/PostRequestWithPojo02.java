package com.techproed.day12;

import com.techproed.pojos.herokuPojo.BookingDatesPojo;
import com.techproed.pojos.herokuPojo.BookingPojo;
import com.techproed.pojos.herokuPojo.BookingResponsePojo;
import com.techproed.testBase.HerokuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequestWithPojo02 extends HerokuAppTestBase {
    /*
    https://restful-booker.herokuapp.com/booking
url’ine aşağıdaki request body gönderildiğinde,
 {
               "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 15000,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
}
Status kodun 200 ve dönen response ‘un
   {
                         "bookingid": 14,
                         "booking": {
                             "firstname": "Selim",
                             "lastname": "Ak",
                             "totalprice": 15000,
                             "depositpaid": true,
                             "bookingdates": {
                                 "checkin": "2020-09-09",
                                 "checkout": "2020-09-21"
                             }
                         }
                        } olduğunu test edin
     */

    @Test
    public void test(){
        spec02.pathParam("first","booking");

        BookingDatesPojo bookingDates=new BookingDatesPojo("2020-09-09","2020-09-21");
        BookingPojo requestData=new BookingPojo("Selim","Ak",15000,true,bookingDates);

        System.out.println("requestData = " + requestData);


        Response response=given().contentType(ContentType.JSON).
                spec(spec02).auth().basic("admin","password123").
                body(requestData).when().
                post("/{first}");

        BookingResponsePojo actualData=response.as(BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);


        assertEquals(200,response.statusCode());

        assertEquals(requestData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(requestData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(requestData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(requestData.isDepositpaid(),actualData.getBooking().isDepositpaid());

        assertEquals(requestData.getBookingdates().getCheckin(),
                actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(requestData.getBookingdates().getCheckout(),
                actualData.getBooking().getBookingdates().getCheckout());

    }

}
