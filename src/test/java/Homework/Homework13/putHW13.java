package Homework.Homework13;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class putHW13 extends User {
    @Test
    public void UpdateUser() {

        Data.setFirstName("Maha");
        Data.setEmail("Maha@gmail.com");
        System.out.println("Data = " + Data);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(Data)
                //.sessionId(session)
                .put(url + "/" + Data.getUsername());
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .body("code", equalTo(response.getStatusCode())
                        , "type", equalTo(type)
                        , "message", equalTo(Data.getId() + "")
                );

    }


}