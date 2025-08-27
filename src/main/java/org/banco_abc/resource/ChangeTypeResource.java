package org.banco_abc.resource;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.banco_abc.client.ChangeTypeResponse;
import org.banco_abc.service.ChangeTypeService;

@Path("/change-type")
@Produces(MediaType.APPLICATION_JSON)
public class ChangeTypeResource {

    @Inject
    ChangeTypeService changeTypeService;

    @GET
    public Response changeTypeQuery(@QueryParam("dni") @NotBlank(message = "El DNI es obligatorio") String dni) {
        try {
            ChangeTypeResponse response = changeTypeService.queryChangeType(dni);
            return Response.ok(response).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.TOO_MANY_REQUESTS)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
