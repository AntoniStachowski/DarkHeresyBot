package org.example.listeners.responses.modal;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericComponentInteractionCreateEvent;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.requests.restaction.interactions.ModalCallbackAction;

public interface ModalRespondable {
    ModalCallbackAction respondModal(Modal modal);

    static ModalRespondable asModalRespondable(SlashCommandInteractionEvent event) {
        return new ModalRespondableSlashCommandEvent(event);
    }

    static ModalRespondable asModalRespondable(GenericComponentInteractionCreateEvent event) {
        return new ModalRespondableComponentInteractionCreateEvent(event);
    }
}
