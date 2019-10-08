package bayern.jugin.quarkus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@Path("/reactivehello")
public class ReactiveHelloResource {

    private static final Logger LOG = LoggerFactory.getLogger(ReactiveHelloResource.class);

    @Inject
    HelloResponseReactiveRepository helloRepository;

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Optional<HelloResponse>> helloYou(
        @PathParam("name") String you) {
        return helloRepository.findByName(you);
    }

}
