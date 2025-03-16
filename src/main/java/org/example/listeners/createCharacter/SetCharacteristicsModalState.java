package org.example.listeners.createCharacter;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.example.character.Characteristics;
import org.example.listeners.ListenerState;
import org.example.listeners.UnexpectedEventException;

import java.util.List;

import static java.lang.Math.min;

public class SetCharacteristicsModalState extends ListenerState<CreateCharacterContext> {
    private final int offset;
    private final int count;

    private static final int textInputCount = 5;
    private static final String characteristicsModalName = "set-characteristics-modal";

    public SetCharacteristicsModalState(ButtonInteractionEvent event, int offset, int count) {
        this.offset = offset;
        this.count = count;
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
    public ListenerState<CreateCharacterContext> handleModalEvent(ModalInteractionEvent event) throws UnexpectedEventException {
        if (event.getModalId().equals(characteristicsModalName)) {
            List<ModalMapping> modalValues = event.getValues();
            for (ModalMapping modalValue : modalValues) {
                String characteristicId = modalValue.getId();
                Characteristics characteristic = Characteristics.getFromId(characteristicId);
                int value = Integer.parseInt(modalValue.getAsString());
                context.getCharacter().setCharacteristic(characteristic, value);
            }
            if (offset + 5 < Characteristics.values().length) {
                return new SetCharacteristicsState(event, offset + 5, count + 1);
            } else {
                return new SetSimpleSkillsState(event, null);
            }
        }
        throw new UnexpectedEventException();
    }


}
