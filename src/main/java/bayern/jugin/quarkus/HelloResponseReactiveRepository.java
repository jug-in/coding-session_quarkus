package bayern.jugin.quarkus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.client.model.Filters;
import io.quarkus.mongodb.ReactiveMongoClient;
import io.quarkus.mongodb.ReactiveMongoCollection;
import org.bson.Document;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class HelloResponseReactiveRepository {

    private static final Logger LOG = LoggerFactory.getLogger(HelloResponseReactiveRepository.class);

    @Inject
    ReactiveMongoClient mongoClient;

    @Inject
    ObjectMapper objectMapper;

    ReactiveMongoCollection<Document> helloCollection;

    @PostConstruct
    public void initCollection() {
        helloCollection = mongoClient.getDatabase("hello").getCollection("hellos");
    }

    public CompletionStage<Void> insert(HelloResponse response) {
        try {
            Document doc = Document.parse(objectMapper.writeValueAsString(response));
            return helloCollection.insertOne(doc);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletionStage<Optional<HelloResponse>> findByName(String name) {
        try {
            PublisherBuilder<Document> found = helloCollection.find(Filters.eq("name", name));
            return found.map(d -> {
                try {
                    return objectMapper.readValue(d.toJson(), HelloResponse.class);
                } catch (IOException e) {
                    LOG.error("Could not load " + name, e);
                    return null;
                }
            }).findFirst().run();
        } catch (MongoException e) {
            LOG.error("Failed to connect to database", e);
            return CompletableFuture.completedFuture(Optional.empty());
        }
    }
}
