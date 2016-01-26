package api;

import com.netflix.config.ConfigurationManager;
import infra.ServicesClient;
import integration.Services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by mschmidt on 1/20/16.
 */
@Path("/services")
public class Api {


    public Api() {
    }

    @GET
    @Path("/service-status/{name}")
    public String serviceStatus(@PathParam("name") String name) {
        return new ServicesClient(new Services(), name).execute().getStatus();

    }

}
