package Homework;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Homework5 {
    /*
          Given
              https://reqres.in/api/users/23
          When
              User send a GET Request to the url
          Then
              HTTP Status code should be 404
          And
              Status Line should be HTTP/1.1 404 Not Found
          And
              Server is "cloudflare"
          And
              Response body should be empty
       */
    @Test
    public void HW5() {
        String url = "https://reqres.in/api/users/23";
        Response response = given().get(url);
        response.prettyPrint();

        response
                .then()
                .assertThat()
                //HTTP Status code should be 404
                .statusCode(404)
                //Status Line should be HTTP/1.1 404 Not Found
                .statusLine("HTTP/1.1 404 Not Found")
                //Server is "cloudflare"
                .header("Server", "cloudflare")
                //Response body should be empty
                .body("isEmpty()", Matchers.is(true));


    }
}
