package org.example.listeners.test;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.example.character.Character;
import org.example.listeners.ListenerState;
import org.example.listeners.UnexpectedEventException;

public class ChooseSkillTypeState extends ListenerState {
    private final Character character;
    private final InteractionHook hook;
    private static final String simpleSkillTestID = "simple-skill-test";
    private static final String complexSkillTestID = "complex-skill-test";


    public ChooseSkillTypeState (SlashCommandInteractionEvent event, Character character) {
        this.character = character;
        event.reply("Select skill type")
                .addActionRow(
                        Button.primary(simpleSkillTestID, "Simple skill test"),
                        Button.primary(complexSkillTestID, "Specialized skill test")
                ).setEphemeral(true)
                .queue();
        hook = event.getHook();
    }

    @Override
    public ListenerState handleButtonEvent(ButtonInteractionEvent event) throws UnexpectedEventException {
        switch (event.getButton().getId()) {
            case simpleSkillTestID -> {
                hook.deleteOriginal().queue();
                return new ChooseSimpleSkillState(event, character);
            }
            case complexSkillTestID -> {
                hook.deleteOriginal().queue();
                return new ChooseComplexSkillState(event, character);
            }
            default ->
                throw new UnexpectedEventException();
        }
    }
}
