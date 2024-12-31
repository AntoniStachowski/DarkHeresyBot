package org.example.listeners;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;

public abstract class ListenerState {
    public ListenerState handleButtonEvent(ButtonInteractionEvent event) throws UnexpectedEventException {
        throw new UnexpectedEventException();
    }
    public ListenerState handleStringSelectEvent(StringSelectInteractionEvent event) throws UnexpectedEventException {
        throw new UnexpectedEventException();
    }
    public ListenerState handleModalEvent(ModalInteractionEvent event) throws UnexpectedEventException {
        throw new UnexpectedEventException();
    }
}
