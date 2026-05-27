package br.com.lucas.pitanga.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class AnalysisResourceTest {

    @Test
    public void testSimulateAndGate() {
        // Simulando o envio de sinais 1 e 1 para a Porta AND (CD4081)
        String jsonBody = """
            {
                "componentType": "CD4081",
                "inputA": 1,
                "inputB": 1
            }
            """;

        given()
          .contentType(ContentType.JSON)
          .body(jsonBody)
          .when().post("/api/analyze")
          .then()
             .statusCode(200)
             .body("outputResult", is(1))
             .body("ledStatus", is("ON")); // Valida se o LED realmente acende
    }

    @Test
    public void testSimulateNotGate() {
        // Simulando o envio do sinal 0 para a Porta NOT (CD4069)
        String jsonBody = """
            {
                "componentType": "CD4069",
                "inputA": 0,
                "inputB": 0
            }
            """;

        given()
          .contentType(ContentType.JSON)
          .body(jsonBody)
          .when().post("/api/analyze")
          .then()
             .statusCode(200)
             .body("outputResult", is(1))
             .body("ledStatus", is("ON")); // Valida se o LED inverteu o 0 para 1
    }
}
