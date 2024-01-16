package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BookingExtration {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = given()
                .when()
                .get("/booking/15")
                .then().statusCode(200);
    }

    @Test
    public void test001() {
        String FirstName = response.extract().path("firstname");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The First Name is : " + FirstName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test002() {
        String lastName = response.extract().path("lastname");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The last name is : " + lastName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test003() {
        int price = response.extract().path("totalprice");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The price is : " + price);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test004() {
        boolean depositpaid = response.extract().path("depositpaid");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The deposit paid is : " + depositpaid);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test005() {
        HashMap<String, Object> dates = response.extract().path("bookingdates");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The dates are : " + dates);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test006() {
        String needs = response.extract().path("additionalneeds");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The needs is : " + needs);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test007() {
        List<Integer> bookingId = response.extract().path("bookingid");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size is : " + bookingId);
        System.out.println("------------------End of Test---------------------------");
    }
}
