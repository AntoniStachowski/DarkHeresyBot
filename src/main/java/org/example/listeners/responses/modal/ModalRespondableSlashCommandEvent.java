package org.example.listeners.responses.modal;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.requests.restaction.interactions.ModalCallbackAction;

public class ModalRespondableSlashCommandEvent implements ModalRespondable {
    SlashCommandInteractionEvent event;

    ModalRespondableSlashCommandEvent(SlashCommandInteractionEvent event) {
        this.event = event;
    }

    @Override
    public ModalCallbackAction respondModal(Modal modal) {
        return event.replyModal(modal);
    }
}
