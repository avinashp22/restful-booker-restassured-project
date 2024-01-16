package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class BookingCrudTest extends TestBase {


    @Test
    public void averifyBookingCreatedSuccessfully()
    {
        String firstName = "Avi"+TestUtils.getRandomValue();
        String lastName = "Pat"+TestUtils.getRandomValue();
        HashMap<String,String> bookingDates = new HashMap();
        bookingDates.put("checkIn" , "2024-08-08");
        bookingDates.put("checkOut" , "2024-09-09");
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstName);
        bookingPojo.setLastname(lastName);
        bookingPojo.setTotalprice(400);
        bookingPojo.setDepositpaid(true);
        bookingPojo.setBookingdates(bookingDates);
        bookingPojo.setAdditionalneeds("Breakfast");

        Response response =
                given()
                        .header("Content-Type","application/json")
                        .header("cookie" , "token=849bf08fa57f00b" )
                        .body(bookingPojo)
                        .when()
                        .post("/booking");
        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void bVerifyBookingReadSuccessfully()
    {
        Response response = given()
                .when()
                .get("/48");
        response.prettyPrint();
        response.then().statusCode(200);
    }


    @Test
    public void cverifyBookingUpdateSuccessfully() {

        String firstName = "Avi"+TestUtils.getRandomValue();
        String lastName = "Pat"+TestUtils.getRandomValue();
        HashMap<String,String>bookingDates = new HashMap();
        bookingDates.put("checkIn" , "2024-03-03");
        bookingDates.put("checkOut" , "2024-05-05");
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstName);
        bookingPojo.setLastname(lastName);
        bookingPojo.setTotalprice(500);
        bookingPojo.setDepositpaid(true);
        bookingPojo.setAdditionalneeds("Breakfast");
        bookingPojo.setBookingdates(bookingDates);

        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .auth().preemptive().basic("admin", "password123")
                        .body(bookingPojo)
                        .when()
                        .put("/booking/48");
        response.then().statusCode(404);
        response.prettyPrint();
    }

    @Test
    public void zVerifyBookingDeleteSuccessfully() {
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete("/48");
        response.then().statusCode(201);
        response.prettyPrint();
    }

}