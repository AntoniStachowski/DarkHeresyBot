package org.example.listeners;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;

public abstract class StateListener extends ListenerAdapter {
    protected final Map<String, ListenerState> userStates = new HashMap<>();

    protected abstract String getCommandName();

    protected abstract ListenerState getInitialState(SlashCommandInteractionEvent event);

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals(getCommandName())) {
            ListenerState state = getInitialState(event);
            userStates.put(event.getUser().getId(), state);
        }
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        String userId = event.getUser().getId();
        ListenerState state = userStates.get(userId);
        if (state != null) {
            try {
                ListenerState newState = state.handleModalEvent(event);
                userStates.put(userId, newState);
            } catch (UnexpectedEventException e) {
                //TODO: fix
            }
        }
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        String userId = event.getUser().getId();
        ListenerState state = userStates.get(userId);
        if (state != null) {
            try {
                ListenerState newState = state.handleStringSelectEvent(event);
                userStates.put(userId, newState);
            } catch (UnexpectedEventException e) {
                //TODO: fix
            }
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        String userId = event.getUser().getId();
        ListenerState state = userStates.get(userId);
        if (state != null) {
            try {
                ListenerState newState = state.handleButtonEvent(event);
                userStates.put(userId, newState);
            } catch (UnexpectedEventException e) {
                //TODO: fix
            }
        }
    }
}
