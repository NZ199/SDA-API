package Homework.Homework15;

import org.testng.annotations.BeforeMethod;
import pojos.UserPetStorePojo;
import utilities.ObjectMapperUtils;
import org.testng.annotations.BeforeMethod;
import pojos.UserPetStorePojo;
import utilities.ObjectMapperUtils;

public class User {
    //Write an automation test that will create a 'user' then read, update and delete the created
    // user using the "https://documenter.getpostman.com/view/4012288/TzK2bEa8" document.

UserPetStorePojo Data;
//Set the url
String url = "https://documenter.getpostman.com/view/4012288/TzK2bEa8";
String type = "unknown";

@BeforeMethod
public void setUp() {

    String strJson =
            """
                    {
                    "firstName": "Nouf",
                    "lastName": "Alsubhi",
                    "birthdate": "2001-01-01",
                    "email": "noufa@gmail.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "KSA"
                    }""";

    Data = ObjectMapperUtils.convertJsonToPojo(strJson, UserPetStorePojo.class);




}}