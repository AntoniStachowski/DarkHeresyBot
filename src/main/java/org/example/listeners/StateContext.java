package org.example.listeners;

import org.example.listeners.responses.ResponsePublisher;

public abstract class StateContext {
    private final ResponsePublisher publisher;

    protected StateContext(ResponsePublisher publisher) {
        this.publisher = publisher;
    }

    public ResponsePublisher getPublisher() {
        return publisher;
    }
}
