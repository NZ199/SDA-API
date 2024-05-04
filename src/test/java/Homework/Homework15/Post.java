package Homework.Homework15;
import base_urls.UserURL;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserContactsPojo;
import pojos.UserPetStorePojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class Post extends UserURL {
    public static String userId;
    public static String Token = "";
    public static int version;
    public static UserContactsPojo expectedData;


    @Test
    public void CreateUser() {
        //set the url
        spec.pathParam("first", "users");

        //Set the exacted data
        String strJson = """
                {
                    "firstName": "Nouf",
                    "lastName": "Alsubhi",
                    "email": "nouf@gmail.com",
                    "password": "Password"
                }""";

        expectedData = ObjectMapperUtils.convertJsonToPojo(strJson, UserContactsPojo.class);

        //send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        userId = response.jsonPath().getString("user._id");
        Token = response.jsonPath().getString("Token");

        //Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("user.firstName", equalTo(expectedData.getFirstName()),
                        "user.lastName",equalTo(expectedData.getLastName()),
                        "user.email",equalTo(expectedData.getEmail()));


    }
}