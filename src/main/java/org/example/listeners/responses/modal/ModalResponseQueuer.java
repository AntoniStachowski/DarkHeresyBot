package org.example.listeners.responses.modal;

public class ModalResponseQueuer implements ModalResponsePublisher<Void> {
    @Override
    public Void publish(ModalResponseBuilder response) {
        response.build().queue();
        return null;
    }
}
