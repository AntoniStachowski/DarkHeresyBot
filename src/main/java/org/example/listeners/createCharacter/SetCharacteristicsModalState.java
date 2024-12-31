package org.example.listeners.createCharacter;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.example.character.Character;
import org.example.character.CharacterManager;
import org.example.character.Characteristics;
import org.example.listeners.ListenerState;
import org.example.listeners.UnexpectedEventException;

import java.util.List;

import static java.lang.Math.min;

public class SetCharacteristicsModalState extends ListenerState {
    private final Character character;
    private final int offset;
    private final int count;
    private CharacterManager characterManager;

    private static final int textInputCount = 5;
    private static final String characteristicsModalName = "set-characteristics-modal";

    public SetCharacteristicsModalState(ButtonInteractionEvent event, Character character, int offset, int count, CharacterManager characterManager) {
        this.character = character;
        this.offset = offset;
        this.count = count;
        this.characterManager = characterManager;
        Characteristics[] characteristics = Characteristics.values();
        Modal.Builder modalBuilder = Modal.create(characteristicsModalName, "Set characteristics (pt. " + count + ")");
        for(int i = offset; i < min(offset + textInputCount, characteristics.length); i++) {
            modalBuilder.addActionRow(
                    TextInput.create(characteristics[i].getId(), characteristics[i].getName(), TextInputStyle.SHORT).build()
            );
        }
        event.replyModal(modalBuilder.build()).queue();
    }

    @Override
    public ListenerState handleModalEvent(ModalInteractionEvent event) throws UnexpectedEventException {
        if (event.getModalId().equals(characteristicsModalName)) {
            List<ModalMapping> modalValues = event.getValues();
            for (ModalMapping modalValue : modalValues) {
                String characteristicId = modalValue.getId();
                Characteristics characteristic = Characteristics.getFromId(characteristicId);
                int value = Integer.parseInt(modalValue.getAsString());
                character.setCharacteristic(characteristic, value);
            }
            if (offset + 5 < Characteristics.values().length) {
                return new SetCharacteristicsState(event, character, offset + 5, count + 1, characterManager);
            } else {
                return new SetSimpleSkillsState(event, character, null, characterManager);
            }
        }
        throw new UnexpectedEventException();
    }


}
