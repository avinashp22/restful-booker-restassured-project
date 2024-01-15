package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class CrudTest extends TestBase {

    static String firstname = "Johny";
    static String lastname = "English";
    static int totalprice = 150;
    static boolean depositpaid = true;
    //static HashMap<String, String> bookingdates;
    static String additionalneeds = "Breakfast";
    static String username = "admin";
    static String password = "password123";

    @Test
    public void averifyBookingCreatedSuccessfully() {
        HashMap<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-07-01");
        bookingdates.put("checkout", "2019-07-25");
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setAdditionalneeds(additionalneeds);
        bookingPojo.setBookingdates(bookingdates);


        Response response = given()
                .header("Content-Type", "application/json")
                .body(bookingPojo)
                .when()
                .post("/booking");
        response.then().log().all().statusCode(200);
        response.prettyPrint();
    }


    @Test
    public void bVerifyBookingReadSuccessfully() {
        Response response = given()
                .when()
                .get("/booking/5964");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void cverifyBookingUpdateSuccessfully() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Michel");
        bookingPojo.setLastname("Smith");
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setAdditionalneeds("Dinner");
        HashMap<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2023-08-01");
        bookingdates.put("checkout", "2023-08-25");
        bookingPojo.setBookingdates(bookingdates);
        /*AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername(username);
        authPojo.setPassword(password);*/


        Response response = given()
                .auth().preemptive()
                .basic("admin", "password123")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token =abc123")
                .body(bookingPojo)
                .when()
                .put("/booking/5964");
        response.then().log().all().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void zVerifyBookingDeleteSuccessfully() {


        Response response = given()
                .auth().preemptive()
                .basic("admin", "password123")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token = abc123")

                .when()
                .delete("/booking/5964");
        response.then().statusCode(201);
        response.prettyPrint();


    }
}