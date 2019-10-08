package bayern.jugin.quarkus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Optional;

@ApplicationScoped
public class HelloResponseRepository {

    private static final Logger LOG = LoggerFactory.getLogger(HelloResponseRepository.class);

    @Inject
    MongoClient mongoClient;

    @Inject
    ObjectMapper objectMapper;

    MongoCollection<Document> helloCollection;

    @PostConstruct
    public void initCollection() {
        helloCollection = mongoClient.getDatabase("hello").getCollection("hellos");
    }

    public void insert(HelloResponse response) {
        try {
            Document doc = Document.parse(objectMapper.writeValueAsString(response));
            helloCollection.insertOne(doc);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<HelloResponse> findByName(String name) {
        try {
            FindIterable<Document> found = helloCollection.find(Filters.eq("name", name));
            return Optional.ofNullable(found.iterator().tryNext()).map(d -> {
                try {
                    return objectMapper.readValue(d.toJson(), HelloResponse.class);
                } catch (IOException e) {
                    LOG.error("Could not load " + name, e);
                    return null;
                }
            });
        } catch (MongoException e) {
            LOG.error("Failed to connect to database", e);
            return Optional.empty();
        }
    }
}
