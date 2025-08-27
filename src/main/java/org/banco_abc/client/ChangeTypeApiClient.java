package org.banco_abc.client;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/tipo-cambio")
@RegisterRestClient(configKey = "tipo-cambio-api")
public interface ChangeTypeApiClient {

    @GET
    @Path("/today.json")
    @Produces(MediaType.APPLICATION_JSON)
    ChangeTypeResponse getChangeType();
}
