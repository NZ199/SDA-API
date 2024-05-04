package Homework.Homework15;

import base_urls.UserURL;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserContactsPojo;
import utilities.ObjectMapperUtils;

import static Homework.Homework15.Post.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Put extends UserURL {

    @Test
    public void updateUserTest() {
        //set the url
        spec.pathParams("first", "users","second","me");


        //Set the exacted data
        String strJson = """
                {
                    "firstName": "Nouf",
                    "lastName": "Alsubhi",
                    "email": "nouf@gmail.com",
                    "password": "Password"
                }""";

        expectedData.setFirstName("Noufa");
        expectedData.setLastName("Alsubhi");


        //send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();


        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("_id",equalTo(userId),
                        "firstName", equalTo(expectedData.getFirstName()),
                        "lastName",equalTo(expectedData.getLastName()),
                        "email",equalTo(expectedData.getEmail()),
                        "__v",equalTo(version));


    }
}