package org.example.listeners;

import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class StateListener<T extends StateContext> extends ListenerAdapter {
    protected final Map<String, ListenerState<T>> userStates = new HashMap<>();

    protected abstract String getCommandName();

    protected final Map<String, T> contexts = new HashMap<>();

    protected abstract ListenerState<T> getInitialState(SlashCommandInteractionEvent event);

    protected abstract T getInitialContext(SlashCommandInteractionEvent event);

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals(getCommandName())) {
            T context = getInitialContext(event);
            contexts.put(event.getUser().getId(), context);

            ListenerState<T> state = getInitialState(event);
            state.setContext(context);
            userStates.put(event.getUser().getId(), state);
        }
    }

    private <U extends GenericInteractionCreateEvent> void onInteraction(U event, TransitionFunction<U, T> trans) {
        String userId = event.getUser().getId();
        ListenerState<T> state = userStates.get(userId);
        T context = contexts.get(userId);
        if (state != null) {
            try {
                ListenerState<T> newState = trans.call(state, event);
                if (newState != null) {
                    newState.setContext(context);
                }
                userStates.put(userId, newState);
            } catch (UnexpectedEventException e) {
                //TODO: fix
            }
        }
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        onInteraction(event, ListenerState::handleModalEvent);
    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        onInteraction(event, ListenerState::handleStringSelectEvent);
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        onInteraction(event, ListenerState::handleButtonEvent);
    }
}
