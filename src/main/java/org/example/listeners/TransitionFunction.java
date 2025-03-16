package org.example.listeners;

@FunctionalInterface
public interface TransitionFunction<U, T> {
    ListenerState<T> call(ListenerState<T> state, U event) throws UnexpectedEventException;
}
