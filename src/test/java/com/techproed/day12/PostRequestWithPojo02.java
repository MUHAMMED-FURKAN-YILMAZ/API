package com.techproed.day12;

import com.techproed.pojos.BookingDatesPojo;
import com.techproed.pojos.BookingPojo;
import com.techproed.pojos.BookingResponsePojo;
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
        spec02.pathParams("first","booking");

        BookingDatesPojo bookingDates=new BookingDatesPojo("2020-09-09","2020-09-21");
        BookingPojo requestData=new BookingPojo("Selim","Ak",15000,true,bookingDates);
        BookingResponsePojo expectedData=new BookingResponsePojo(14,requestData);
        System.out.println("requestData = " + requestData);
        System.out.println("expectedData = " + expectedData);

        Response response=given().contentType(ContentType.JSON).
                spec(spec02).auth().basic("admin","password123").
                body(requestData).when().
                post("/{first}");

        BookingResponsePojo actualData=response.as(BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);


        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getBookingid(),actualData.getBookingid());
        assertEquals(expectedData.getBooking().getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getBooking().getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getBooking().getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getBooking().isDepositpaid(),actualData.getBooking().isDepositpaid());
        assertEquals(expectedData.getBooking().getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedData.getBooking().getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());

    }

}
