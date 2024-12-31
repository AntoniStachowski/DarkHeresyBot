package org.example.listeners.createCharacter;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.example.character.Character;
import org.example.character.CharacterManager;
import org.example.listeners.ListenerState;
import org.example.listeners.UnexpectedEventException;

public class SetCharacteristicsState extends ListenerState {
    private static final String setCharacteristicsFirstName = "set-characteristics";
    private static final String abortCreationName = "stop-creating";
    private final InteractionHook hook;
    private final Character character;
    private final int offset;
    private final int count;
    private final CharacterManager characterManager;

    public SetCharacteristicsState(ModalInteractionEvent event, Character character, int offset, int count, CharacterManager characterManager) {
        this.character = character;
        this.offset = offset;
        this.count = count;
        this.characterManager = characterManager;

        event.reply("Hello " + character.getName() + "! Please set your characteristics.")
                .addActionRow(
                        Button.primary(setCharacteristicsFirstName, "Set characteristics (pt. " + count + ")"),
                        Button.danger(abortCreationName, "Abort")
                ).setEphemeral(true).queue();
        hook = event.getHook();
    }

    @Override
    public ListenerState handleButtonEvent(ButtonInteractionEvent event) throws UnexpectedEventException {
        switch (event.getButton().getId()) {
            case abortCreationName -> {
                hook.deleteOriginal().queue();
                event.reply("Creation cancelled.").setEphemeral(true).queue();
                return null;
            }
            case setCharacteristicsFirstName -> {
                hook.deleteOriginal().queue();
                return new SetCharacteristicsModalState(event, character, offset, count, characterManager);
            }
            default -> throw new UnexpectedEventException();
        }
    }

}
