package org.example.listeners;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;

public abstract class ListenerState<T> {
    protected T context;

    public ListenerState<T> handleButtonEvent(ButtonInteractionEvent event) throws UnexpectedEventException {
        throw new UnexpectedEventException();
    }
    public ListenerState<T> handleStringSelectEvent(StringSelectInteractionEvent event) throws UnexpectedEventException {
        throw new UnexpectedEventException();
    }
    public ListenerState<T> handleModalEvent(ModalInteractionEvent event) throws UnexpectedEventException {
        throw new UnexpectedEventException();
    }

    public void setContext(T context) {
        this.context = context;
        whenContextSet();
    }

    protected void whenContextSet() {}
}
