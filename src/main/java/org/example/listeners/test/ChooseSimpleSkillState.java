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
import org.example.character.Character;
import org.example.character.Characteristics;
import org.example.character.skills.SimpleSkills;
import org.example.listeners.ListenerState;
import org.example.listeners.UnexpectedEventException;

import java.util.ArrayList;
import java.util.List;

public class ChooseSimpleSkillState extends ListenerState {
    private static final String skillSelectId = "skill-select";
    private static final String cancelId = "cancel";
    private static final String continueID = "continue";

    private final Character character;
    private final InteractionHook hook;
    private SimpleSkills chosenSkill;


    private List<LayoutComponent> getMessageComponents(){
        StringSelectMenu.Builder skillSelectionBuilder = StringSelectMenu.create(skillSelectId);
        for (SimpleSkills skill: SimpleSkills.values()) {
            skillSelectionBuilder.addOption(
                    skill.name, skill.name
            );
        }
        if (chosenSkill != null) {
            skillSelectionBuilder.setDefaultOptions(SelectOption.of(chosenSkill.getName(), chosenSkill.getName()));
        }
        return new ArrayList<>(){{
            add(ActionRow.of(skillSelectionBuilder.build()));
            add(ActionRow.of(Button.of(ButtonStyle.DANGER, cancelId, "Cancel"),
                    Button.of(ButtonStyle.SECONDARY, continueID, "Continue").withDisabled(chosenSkill == null)));
        }};
    }

    public ChooseSimpleSkillState(IReplyCallback event, Character character) {
        this.character = character;
        event.reply("Which skill to test?")
                .setComponents(getMessageComponents())
                .setEphemeral(true)
                .queue();
        hook = event.getHook();
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
                Characteristics baseCharacteristic = chosenSkill.mainCharacteristic;
                List<Characteristics> possibleCharacteristics = new ArrayList<>(List.of(chosenSkill.secondaryCharacteristics));
                possibleCharacteristics.add(baseCharacteristic);
                return new ChooseCharacteristicState(event, character, possibleCharacteristics, baseCharacteristic,chosenSkill);
            }
            default ->
                throw new UnexpectedEventException();
        }
    }

    @Override
    public ListenerState handleStringSelectEvent(StringSelectInteractionEvent event) throws UnexpectedEventException {
        boolean matched = false;
        switch (event.getComponentId()) {
            case skillSelectId -> {
                chosenSkill = SimpleSkills.getFromName(event.getValues().get(0));
                matched = true;
            }
        }
        if (matched) {
            hook.editOriginalComponents(getMessageComponents()).queue();
            event.deferEdit().queue();
            return this;
        }
        throw new UnexpectedEventException();
    }
}
