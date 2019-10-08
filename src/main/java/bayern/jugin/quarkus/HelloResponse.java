package bayern.jugin.quarkus;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.Instant;

@RegisterForReflection
public class HelloResponse {

    private String greeting;
    private String name;
    private Instant time;

    public String getGreeting() {
        return greeting;
    }

    public HelloResponse setGreeting(String greeting) {
        this.greeting = greeting;
        return this;
    }

    public String getName() {
        return name;
    }

    public HelloResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Instant getTime() {
        return time;
    }

    public HelloResponse setTime(Instant time) {
        this.time = time;
        return this;
    }

}
