package org.example.listeners.responses.modal;

import net.dv8tion.jda.api.events.interaction.component.GenericComponentInteractionCreateEvent;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.requests.restaction.interactions.ModalCallbackAction;

public class ModalRespondableComponentInteractionCreateEvent implements ModalRespondable{
    public GenericComponentInteractionCreateEvent event;

    ModalRespondableComponentInteractionCreateEvent(GenericComponentInteractionCreateEvent event) {
        this.event = event;
    }

    @Override
    public ModalCallbackAction respondModal(Modal modal) {
        return event.replyModal(modal);
    }
}
