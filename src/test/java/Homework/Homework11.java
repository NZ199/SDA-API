package Homework;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Homework11 {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12

    Note: Print using JsonPath: response.jsonPath().prettyPrint();

*/

    @Test
    public void HW11() {
        // Send GET request and extract response
        String response = RestAssured.when()
                .get("https://automationexercise.com/api/productsList")
                .then()
                .statusCode(200)
                .extract().asString();

        // Parse JSON
        JsonPath jsonPath = JsonPath.from(response);

        // getHW13 total products and count "Women" products
        int totalProducts = jsonPath.getList("products").size();
        int womenProducts = jsonPath.getInt("products.findAll { it.category.usertype.usertype == 'Women' }.size()");

        // Assert number of "Women" products
        assertEquals(12, womenProducts, "Number of 'Women' user type products does not match expected value.");

        // Optional: Pretty print JSON response
        System.out.println("\nPretty Printed JSON Response:");
         jsonPath.prettyPrint();
        System.out.println(totalProducts);
    }
}
