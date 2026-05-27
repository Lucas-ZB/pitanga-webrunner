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
        // Testa se a rota GET está de pé e respondendo com status 200 (OK)
        given()
          .when().get("/api/projects")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCreateProjectEndpoint() {
        // Simulando o envio de um projeto de microeletrônica no formato JSON
        String novoProjetoJson = """
            {
                "name": "Somador Completo 4-Bits",
                "authorName": "Lucas",
                "targetBoard": "TM1638"
            }
            """;

        // Testa se a rota POST recebe o JSON, salva e devolve o status 201 (Created)
        given()
          .contentType(ContentType.JSON)
          .body(novoProjetoJson)
          .when().post("/api/projects")
          .then()
             .statusCode(201)
             .body("id", notNullValue()) // Garante que o banco gerou um ID
             .body("name", is("Somador Completo 4-Bits"))
             .body("verificationStatus", is("PENDING")); // Garante a regra de negócio inicial
    }
}
