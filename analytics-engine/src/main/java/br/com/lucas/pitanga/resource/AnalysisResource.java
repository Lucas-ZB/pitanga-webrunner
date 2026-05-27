package br.com.lucas.pitanga.resource;

import br.com.lucas.pitanga.dto.SimulationRequest;
import br.com.lucas.pitanga.dto.SimulationResult;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/analyze")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnalysisResource {

    @POST
    public Response simulateGates(SimulationRequest request) {
        int result = 0;

        // Simulação da lógica combinacional
        if ("CD4081".equalsIgnoreCase(request.componentType)) {
            // Porta AND
            result = (request.inputA == 1 && request.inputB == 1) ? 1 : 0;
        } else if ("CD4069".equalsIgnoreCase(request.componentType)) {
            // Porta NOT
            result = (request.inputA == 0) ? 1 : 0;
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Componente desconhecido.\"}")
                    .build();
        }

        String led = (result == 1) ? "ON" : "OFF";
        SimulationResult responseResult = new SimulationResult(request.componentType, result, led);

        return Response.ok(responseResult).build();
    }
}
