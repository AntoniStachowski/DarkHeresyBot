package org.example.listeners.responses;

public class ResponseQueuer implements ResponsePublisher<Void> {

    @Override
    public Void publish(ResponseBuilder builder) {
        builder.build().queue();
        return null;
    }
}
