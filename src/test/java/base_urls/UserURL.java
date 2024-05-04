package base_urls;


import Homework.Homework15.Post;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class UserURL {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {
        String Token = Post.Token;
        System.out.println("Token = " + Token);
        spec = new RequestSpecBuilder()
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .addHeader("Authorization", "Bearer " + Token)
                .setContentType(ContentType.JSON)
                .build();

    }
}