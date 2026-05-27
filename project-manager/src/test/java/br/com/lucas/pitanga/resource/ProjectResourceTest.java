package br.com.lucas.pitanga.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class ProjectResourceTest {

    @Test
    public void testListAllProjectsEndpoint() {
        given()
          .when().get("/api/projects")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCreateProjectEndpoint() {
        String novoProjetoJson = """
            {
                "name": "Validação de Porta AND",
                "authorName": "Lucas",
                "targetBoard": "CD4081"
            }
            """;

        given()
          .contentType(ContentType.JSON)
          .body(novoProjetoJson)
          .when().post("/api/projects")
          .then()
             .statusCode(201)
             .body("id", notNullValue())
             .body("verificationStatus", is("ERROR_CONNECTION"));
    }
}
