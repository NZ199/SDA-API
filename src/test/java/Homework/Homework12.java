package Homework;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojos.PetCategoryPojo;
import pojos.PetStorePetPojo;
import pojos.PetTagPojo;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojos.UserPetStorePojo;

public class Homework12 {
    //Write an automation test that will create, read, update, delete a 'pet' using the "https://petstore.swagger.io/" document
    //(All actions must be done on same pet)
    //            (Use Pojo)

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET_RESOURCE = "/pet";
    private static int petId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(priority = 1)
    public void createPetTest() {
        // Create a pet
        UserPetStorePojo pet = new UserPetStorePojo();
        pet.setId(12345);
        pet.setUsername("Nouf");
        pet.setUserStatus("available");

        petId = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post(PET_RESOURCE)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");

        System.out.println("Created pet with ID: " + petId);
    }

    @Test(priority = 2)
    public void readPetTest() {
        // Read the created pet
        UserPetStorePojo retrievedPet = RestAssured
                .given()
                .pathParam("petId", petId)
                .when()
                .get(PET_RESOURCE + "/{petId}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(UserPetStorePojo.class);

        System.out.println("Retrieved pet: " + retrievedPet);
    }

    @Test(priority = 3)
    public void updatePetTest() {
        // Update the created pet
        UserPetStorePojo updatedPet = new UserPetStorePojo();
        updatedPet.setId(petId);
        updatedPet.setUsername("Maximus");
        updatedPet.setUserStatus("sold");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updatedPet)
                .when()
                .put(PET_RESOURCE)
                .then()
                .assertThat()
                .statusCode(200);

        System.out.println("Updated pet with ID: " + petId);
    }

    @Test(priority = 4)
    public void deletePetTest() {
        // delete the created pet
        RestAssured
                .given()
                .pathParam("petId", petId)
                .when()
                .delete(PET_RESOURCE + "/{petId}")
                .then()
                .assertThat()
                .statusCode(200);

        System.out.println("Deleted pet with ID: " + petId);
    }
}
//    private final String url = "https://petstore.swagger.io/v2/pet";
//    private PetCategoryPojo petCategoryPojo;
//    private PetTagPojo petTagPojo;
//    private PetStorePetPojo expectedData;
//
//
//    @BeforeClass
//    public void setup() {
//        //set the expected data
//        long id = System.currentTimeMillis();
//        petCategoryPojo = new PetCategoryPojo(id, "cat");
//        ArrayList<String> photoUrlList = new ArrayList<>();
//        photoUrlList.add("uuu");
//        petTagPojo = new PetTagPojo(id, "cute");
//        ArrayList<PetTagPojo> tagsList = new ArrayList<>();
//        tagsList.add(petTagPojo);
//        expectedData = new PetStorePetPojo(id, petCategoryPojo, "Sukar", photoUrlList, tagsList, "available");
//
//
//    }
//
//    @Test
//    public void create() {
//
//        //Send the request and get the response
//        System.out.println("expectedData = " + expectedData);
//        Response response = given()
//                .body(expectedData)
//                .contentType(ContentType.JSON)
//                .post(url);
//        response.jsonPath().prettyPrint();
//
//        //do assertion
//        responseAssertion(response);
//
//
//    }
//
//    @Test(dependsOnMethods = "create")
//    public void read() {
//        //Send the request and get the response
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .get(url + "/" + expectedData.getId());
//        response.jsonPath().prettyPrint();
//
//        //do assertion
//        responseAssertion(response);
//
//
//
//    }
//
//    @Test(dependsOnMethods = "read")
//    public void update() {
//        //update the expected data
//        expectedData.setName("Ace");
//        expectedData.getCategory().setName("big cat");
//        expectedData.getPhotoUrls().add("ooo");
//        expectedData.getTags().getFirst().setName("white");
//
//
//        //Send the request and get the response
//        System.out.println("expectedData = " + expectedData);
//        Response response = given()
//                .body(expectedData)
//                .contentType(ContentType.JSON)
//                .put(url);
//        response.jsonPath().prettyPrint();
//
//        //do assertion
//        responseAssertion(response);
//
//    }
//
//    @Test(dependsOnMethods = "update")
//    public void delete() {
//
//        //Send the request and get the response
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .delete(url + "/" + expectedData.getId());
//        response.jsonPath().prettyPrint();
//
//        //do assertion
//        response
//                .then()
//                .statusCode(200)
//                .body("code", equalTo(response.getStatusCode())
//                        , "type", equalTo("unknown")
//                        , "message", equalTo(expectedData.getId().toString())
//                );
//    }
//
//    private void responseAssertion( Response response){
//        response
//                .then()
//                .statusCode(200)
//                .body("id", equalTo(expectedData.getId())
//                        , "category.id", equalTo(petCategoryPojo.getId())
//                        , "category.name", equalTo(expectedData.getCategory().getName())
//                        , "name", equalTo(expectedData.getName())
//                        , "photoUrls", equalTo(expectedData.getPhotoUrls())
//                        , "tags.id[0]", equalTo(petTagPojo.getId())
//                        , "tags.name[0]", equalTo(expectedData.getTags().getFirst().getName())
//                        , "status", equalTo(expectedData.getStatus())
//                );
//    }

