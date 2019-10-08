package bayern.jugin.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class HelloResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
            .when().get("/hello")
            .then()
            .statusCode(200)
            .body(is("hello"));
    }

    @Test
    public void testHelloYouEndpoint() {
        String name = "cowboy";
        given()
            .when().get("/hello/" + name)
            .then()
            .statusCode(202)
            .body(allOf(containsString("howdy"), containsString("howdy")));
    }

    @Test
    public void testHelloYouEndpointValidation() {
        String name = "§(!*";
        given()
            .when().get("/hello/" + name)
            .then()
            .statusCode(400)
            .body(containsString("Is that really your name?"));
    }

}
