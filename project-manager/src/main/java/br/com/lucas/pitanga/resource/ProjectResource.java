package br.com.lucas.pitanga.resource;

import br.com.lucas.pitanga.entity.CircuitProject;
import br.com.lucas.pitanga.client.AnalyticsClient;
import br.com.lucas.pitanga.dto.SimulationRequest;
import br.com.lucas.pitanga.dto.SimulationResult;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

    @RestClient
    AnalyticsClient analyticsClient;

    @GET
    public List<CircuitProject> listAll() {
        return CircuitProject.listAll();
    }

    @POST
    @Transactional
    public Response create(CircuitProject project) {
        try {
            // 1. Prepara a requisição de teste para enviar ao Motor B
            SimulationRequest req = new SimulationRequest();
            req.componentType = project.targetBoard;
            req.inputA = 1;
            req.inputB = 1;

            // 2. O microsserviço A faz uma requisição HTTP para o microsserviço B!
            SimulationResult result = analyticsClient.simulateGates(req);

            // 3. Atualiza o status do banco baseado na resposta analítica
            if ("ON".equals(result.ledStatus)) {
                project.verificationStatus = "PASSED";
            } else {
                project.verificationStatus = "FAILED";
            }
        } catch (Exception e) {
            // Se o motor estiver desligado, não derrubamos o sistema, apenas avisamos.
            project.verificationStatus = "ERROR_CONNECTION";
        }

        project.persist();
        return Response.status(Response.Status.CREATED).entity(project).build();
    }
}
