package Homework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Homework16
{
    //Write an automation test that will add a 'contact' then read, update and delete the created contact then negative assert the deleted contact
    // using the "https://documenter.getpostman.com/view/4012288/TzK2bEa8" document.
        private static final String BASE_URL = "https://thinking-tester-contact-list.herokuapp.com";
        private static String accessToken;
        private static String contactId;

        @BeforeAll
        public static void setup() {
            // Replace with your actual access token retrieval logic
            accessToken = "YOUR_ACCESS_TOKEN";
        }

        @Test
        public void HW16() {
            // Add a contact
            String requestBody = "{\"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"jdoe@example.com\"}";
            JsonPath response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + accessToken)
                    .body(requestBody)
                    .post(BASE_URL + "/contacts")
                    .then()
                    .statusCode(201)
                    .extract().jsonPath();

            contactId = response.getString("_id");

            // Read the added contact
            response = RestAssured.given()
                    .header("Authorization", "Bearer " + accessToken)
                    .get(BASE_URL + "/contacts/" + contactId)
                    .then()
                    .statusCode(200)
                    .extract().jsonPath();

            assertThat(response.getString("firstName"), equalTo("John"));
            assertThat(response.getString("lastName"), equalTo("Doe"));
            assertThat(response.getString("email"), equalTo("jdoe@example.com"));

            // Update the contact
            requestBody = "{\"firstName\": \"Jane\", \"lastName\": \"Doe\"}";
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + accessToken)
                    .body(requestBody)
                    .put(BASE_URL + "/contacts/" + contactId)
                    .then()
                    .statusCode(200)
                    .extract().jsonPath();

            assertThat(response.getString("firstName"), equalTo("Jane"));

            // Delete the contact
            RestAssured.given()
                    .header("Authorization", "Bearer " + accessToken)
                    .delete(BASE_URL + "/contacts/" + contactId)
                    .then()
                    .statusCode(204);

            // Negative assert - Get the deleted contact (should fail)
            RestAssured.given()
                    .header("Authorization", "Bearer " + accessToken)
                    .get(BASE_URL + "/contacts/" + contactId)
                    .then()
                    .statusCode(404);
        }
    }


