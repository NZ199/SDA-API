package Homework.Homework13;
import org.testng.annotations.BeforeMethod;
import pojos.UserPetStorePojo;
import utilities.ObjectMapperUtils;
public class User {
    //Write an automation test that will create a 'user' then read, update and delete the created user
    // using the "https://petstore.swagger.io/" document. (Create a classes for each request.)
    UserPetStorePojo Data;
    //Set the url
    String url = "https://petstore.swagger.io/v2/user";
    String type = "unknown";

    @BeforeMethod
    public void setUp() {

        String strJson =
                """
                        {
                          "id": 1,
                          "username": "NoufA",
                          "firstName": "Nouf",
                          "lastName": "Alsubhi",
                          "email": "Nouf@gmail.com",
                          "password": "12345",
                          "phone": "555555",
                          "userStatus": 0
                        }""";

        Data = ObjectMapperUtils.convertJsonToPojo(strJson, UserPetStorePojo.class);
    }



}