package petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Pet {
    String uri = "https://petstore.swagger.io/v2";

    @DisplayName("Cadastrar novo Pet a Loja")
    @Test
    public void cadastroPet(){
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 10,\n" +
                        "    \"name\": \"Cachorro\"\n" +
                        "  },\n" +
                        "  \"name\": \"Snoopy\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 2,\n" +
                        "      \"name\": \"Dog\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .log().all()
                .when()
                .post(uri + "/pet")
                .then()
                .body("name", is("Snoopy"))
                .body("status", is("available"))
                .body("category.name", is("Cachorro"))
                .body("tags.name", contains("Dog"))
                .log().all()
                .statusCode(200);
    }


}
