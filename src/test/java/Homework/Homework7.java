package Homework;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import java.util.List;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
public class Homework7 {
      /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
      @Test
      public void HW7() {
          String url = "https://reqres.in/api/unknown/";
          Response response = given().get(url);
          response.prettyPrint();
        //Assertions
        // Verify that the status code is 200
          response
                  .then()
                  .assertThat()
                  .statusCode(200);

        // Extract JSON data
          JsonPath jsonPath = new JsonPath(response.asString()).setRootPath("data");

        // Print all pantone_values
          List<String> pantoneValues = jsonPath.getList("pantone_value");
          System.out.println("pantoneValues = " + pantoneValues);

        // Print all ids greater than 3 on the console
          List<Integer> idList = jsonPath.getList("findAll{it.id > 3}.id");
          System.out.println("idList = " + idList);
          Assert.assertEquals(idList.size(), 3);

        // Print all names whose ids are less than 3 on the console
          List<String> nameList = jsonPath.getList("findAll{it.id < 3}.name");
          System.out.println("nameList = " + nameList);
          Assert.assertEquals(nameList.size(), 2);
      }
}


