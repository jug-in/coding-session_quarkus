package bayern.jugin.quarkus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;

@Path("/hello")
public class HelloResource {

    private static final Logger LOG = LoggerFactory.getLogger(HelloResource.class);

    @Inject
    HelloResponseRepository helloRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloYou(
        @PathParam("name") @Pattern(regexp = "\\w+", message = "Is that really your name?") String you,
        @Context HttpServletResponse response) {
        return Response
            .status(Response.Status.ACCEPTED)
            .entity(getHelloResponse(you))
            .build();
    }

    private HelloResponse getHelloResponse(String name) {
        return helloRepository.findByName(name)
            .orElseGet(() -> {
                HelloResponse hello = new HelloResponse().setGreeting("howdy").setName(name).setTime(Instant.now());
                try {
                    helloRepository.insert(hello);
                } catch (Exception e) {
                    LOG.error("Failed to insert " + name, e);
                }
                return hello;
            });
    }
}
