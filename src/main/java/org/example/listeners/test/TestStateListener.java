package org.example.listeners.test;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.character.Character;
import org.example.character.CharacterManager;
import org.example.listeners.ListenerState;
import org.example.listeners.StateListener;

public class TestStateListener extends StateListener {
    private final CharacterManager characterManager;

    @Override
    protected String getCommandName() {
        return "test";
    }

    @Override
    protected ListenerState getInitialState(SlashCommandInteractionEvent event) {
        Character character = characterManager.getCharacter(event.getUser().getId());
        return new ChooseSkillTypeState(event, character);
    }

    public TestStateListener(CharacterManager characterManager){
        this.characterManager = characterManager;
    }
}
