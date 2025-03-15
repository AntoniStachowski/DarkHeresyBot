package org.example.listeners.responses;

import org.example.listeners.responses.modal.ModalResponseBuilder;

public class ResponseQueuer implements ResponsePublisher {
    @Override
    public void publish(ResponseBuilder builder) {
        builder.build().queue();
    }

    @Override
    public void publish(ModalResponseBuilder builder) {
        builder.build().queue();
    }
}
