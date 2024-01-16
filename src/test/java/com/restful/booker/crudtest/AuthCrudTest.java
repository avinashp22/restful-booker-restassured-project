package com.restful.booker.crudtest;

import com.restful.booker.model.AuthPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthCrudTest {

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/auth";
    }

    @Test
    public void T1verifyTokenreatedSuccessfully() {

        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername("admin");
        authPojo.setPassword("password123");

        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .when()
                        .body(authPojo)
                        .post();
        response.prettyPrint();
        response.then().statusCode(200);
    }
}