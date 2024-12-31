package org.example.listeners.test;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.callbacks.IReplyCallback;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.example.Skill;
import org.example.character.Character;
import org.example.character.Characteristics;
import org.example.listeners.ListenerState;
import org.example.listeners.UnexpectedEventException;

import java.util.ArrayList;
import java.util.List;

public class ChooseCharacteristicState extends ListenerState {

    private static final String characteristicSelectId = "characteristic-select";
    private static final String cancelId = "cancel";
    private static final String continueID = "continue";
    private final Character character;
    private final InteractionHook hook;
    private final List<Characteristics> possibleCharacteristics;
    private Characteristics chosenCharacteristic;
    private final Skill skill;

    private List<LayoutComponent> getMessageComponents() {
        StringSelectMenu.Builder characteristicSelectionBuilder = StringSelectMenu.create(characteristicSelectId);
        for (Characteristics characteristics: possibleCharacteristics) {
            characteristicSelectionBuilder.addOption(
                    characteristics.getId(), characteristics.getName(), characteristics.emoji
            );
        }
        characteristicSelectionBuilder.setDefaultOptions(SelectOption.of(chosenCharacteristic.getId(), chosenCharacteristic.getName()));

        return new ArrayList<>(){{
            add(ActionRow.of(characteristicSelectionBuilder.build()));
            add(ActionRow.of(Button.of(ButtonStyle.DANGER, cancelId, "Cancel"),
                    Button.of(ButtonStyle.SECONDARY, continueID, "Continue")));
        }};
    }

    public ChooseCharacteristicState(IReplyCallback event,
                                     Character character,
                                     List<Characteristics> possibleCharacteristics,
                                     Characteristics baseCharacteristic, Skill skill) {
        this.character = character;
        this.possibleCharacteristics = possibleCharacteristics;
        this.chosenCharacteristic = baseCharacteristic;
        this.skill = skill;
        event.reply("Which characteristic do you want to use?")
                .setComponents(getMessageComponents())
                .setEphemeral(true)
                .queue();
        this.hook = event.getHook();
    }

    @Override
    public ListenerState handleButtonEvent(ButtonInteractionEvent event) throws UnexpectedEventException {
        Button button = event.getButton();
        switch (button.getId()) {
            case cancelId -> {
                hook.deleteOriginal().queue();
                return null;
            }
            case continueID -> {
                hook.deleteOriginal().queue();
                return new TestResultState(event, character, skill, chosenCharacteristic);
            }
            default ->
                    throw new UnexpectedEventException();
        }
    }

    @Override
    public ListenerState handleStringSelectEvent(StringSelectInteractionEvent event) throws UnexpectedEventException {
        switch (event.getComponentId()) {
            case characteristicSelectId -> {
                chosenCharacteristic = Characteristics.getFromId(event.getValues().get(0));
            }
            default ->
                throw new UnexpectedEventException();

        }
        event.deferEdit().queue();
        return this;

    }

}
