package org.example.listeners.createCharacter;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.character.CharacterManager;
import org.example.listeners.ListenerState;
import org.example.listeners.StateListener;
import org.example.listeners.responses.ResponseQueuer;

public class CreateCharacterStateListener extends StateListener<CreateCharacterContext> {
    private final CharacterManager characterManager;

    public CreateCharacterStateListener(CharacterManager characterManager) {
        this.characterManager = characterManager;
    }

    @Override
    protected String getCommandName() {
        return "create-character";
    }

    @Override
    protected ListenerState<CreateCharacterContext> getInitialState(SlashCommandInteractionEvent event) {
        return new SelectNameState(event);
    }

    @Override
    protected CreateCharacterContext getInitialContext(SlashCommandInteractionEvent event) {
        return new CreateCharacterContext(new ResponseQueuer(), characterManager);
    }
}
