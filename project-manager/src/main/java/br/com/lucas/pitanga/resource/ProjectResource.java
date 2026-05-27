package br.com.lucas.pitanga.resource;

import br.com.lucas.pitanga.entity.CircuitProject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/api/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

    
    @GET
    public List<CircuitProject> listAll() {
        return CircuitProject.listAll();
    }

    
    @POST
    @Transactional 
    public Response create(CircuitProject project) {
        project.verificationStatus = "PENDING"; 
        project.persist();
        return Response.status(Response.Status.CREATED).entity(project).build();
    }
}
