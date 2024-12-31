package org.example.listeners.createCharacter;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.example.character.Character;
import org.example.character.CharacterManager;
import org.example.listeners.ListenerState;
import org.example.listeners.UnexpectedEventException;

public class SelectNameState extends ListenerState {
    private static final String nameTextInputName = "char-name";
    private static final String nameModalName = "char-name-modal";
    private final CharacterManager characterManager;

    public SelectNameState (SlashCommandInteractionEvent event, CharacterManager characterManager) {
        this.characterManager = characterManager;
        event.replyModal(Modal.create(nameModalName, "Your character's name")
                        .addActionRow(
                                TextInput.create(nameTextInputName, "Name", TextInputStyle.SHORT).build()
                        )
                        .build())
                .queue();
    }

    @Override
    public ListenerState handleModalEvent(ModalInteractionEvent event) throws UnexpectedEventException {
        if (event.getModalId().equals(nameModalName)) {
            String name = event.getValue(nameTextInputName).getAsString();
            Character character = new Character(name);
            return new SetCharacteristicsState(event, character, 0, 0, characterManager);
        }
        throw new UnexpectedEventException();
    }
}
