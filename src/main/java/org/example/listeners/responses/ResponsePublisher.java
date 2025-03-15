package org.example.listeners.responses;

public interface ResponsePublisher<T> {
    T publish(ResponseBuilder builder);
}
