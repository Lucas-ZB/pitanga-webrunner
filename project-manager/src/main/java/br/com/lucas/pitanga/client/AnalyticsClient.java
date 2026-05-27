package br.com.lucas.pitanga.client;

import br.com.lucas.pitanga.dto.SimulationRequest;
import br.com.lucas.pitanga.dto.SimulationResult;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/analyze")
@RegisterRestClient(configKey="analytics-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AnalyticsClient {
    @POST
    SimulationResult simulateGates(SimulationRequest request);
}
