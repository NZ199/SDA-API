package Homework;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserPetStorePojo;
import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
public class Homework9 {
    /*
    Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
    */

    @Test
    public void HW9() {
        // Define API endpoint and expected response data
        final String userUrl = "https://petstore.swagger.io/v2/user";
        final Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("code", 200);
        expectedResponse.put("type", "unknown");
        expectedResponse.put("message", "1");

        // Create user data object
        UserPetStorePojo userData = new UserPetStorePojo(
                1, "NOUFA", "Nouf", "ALSubhi", "Nouf@gmail.com", "098765", "0555555");
        // Send POST request and capture response
        Response response = RestAssured.given()
                .body(userData)
                .contentType(ContentType.JSON)
                .post(userUrl);

        // Print response for debugging (optional)
         response.prettyPrint();

        // Verify response status code and body
        response.then()
                .assertThat()
                .statusCode(200)
                .body("code", equalTo(expectedResponse.get("code")))
                .body("type", equalTo(expectedResponse.get("type")))
                .body("message", equalTo(expectedResponse.get("message")));
    }
}

