package org.example.listeners.createCharacter;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.character.CharacterManager;
import org.example.listeners.ListenerState;
import org.example.listeners.StateListener;

public class CreateCharacterStateListener extends StateListener {
    private final CharacterManager characterManager;

    public CreateCharacterStateListener(CharacterManager characterManager) {
        this.characterManager = characterManager;
    }

    @Override
    protected String getCommandName() {
        return "create-character";
    }

    @Override
    protected ListenerState getInitialState(SlashCommandInteractionEvent event) {
        return new SelectNameState(event, characterManager);
    }
}
