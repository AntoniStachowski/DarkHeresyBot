package org.example.listeners.responses;

import org.example.listeners.responses.modal.ModalResponseBuilder;

public interface ResponsePublisher {
    void publish(ResponseBuilder builder);

    void publish(ModalResponseBuilder builder);
}
