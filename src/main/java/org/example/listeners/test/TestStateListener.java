package org.example.listeners.test;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.character.Character;
import org.example.character.CharacterManager;
import org.example.listeners.ListenerState;
import org.example.listeners.StateListener;
import org.example.listeners.responses.ResponseQueuer;

public class TestStateListener extends StateListener<TestContext> {
    private final CharacterManager characterManager;

    @Override
    protected String getCommandName() {
        return "test";
    }

    @Override
    protected ListenerState<TestContext> getInitialState(SlashCommandInteractionEvent event) {
        return new ChooseSkillTypeState(event);
    }

    @Override
    protected TestContext getInitialContext(SlashCommandInteractionEvent event) {
        Character character = characterManager.getCharacter(event.getUser().getId());
        return new TestContext(new ResponseQueuer(), character);
    }

    public TestStateListener(CharacterManager characterManager){
        this.characterManager = characterManager;
    }
}
