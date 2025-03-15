package org.example.listeners.responses.modal;

public interface ModalResponsePublisher<T> {
    T publish(ModalResponseBuilder response);
}
