package org.example.listeners.test;

import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.callbacks.IReplyCallback;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.example.Skill;
import org.example.character.Character;
import org.example.character.Characteristics;
import org.example.listeners.ListenerState;
import org.example.listeners.UnexpectedEventException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestResultState extends ListenerState {
    Integer throwResult;
    Integer skillValue;
    InteractionHook hook;

    private static final String difficultySelectId = "difficulty-select";

    private static List<LayoutComponent> getMessageComponents() {
        StringSelectMenu.Builder difficultySelectionBuilder = StringSelectMenu.create(difficultySelectId);
        for (Difficulties difficulty: Difficulties.values()) {
            difficultySelectionBuilder.addOption(
                    difficulty.getName(), difficulty.getId()
            );
        }

        return new ArrayList<>(){{
            add(ActionRow.of(difficultySelectionBuilder.build()));
        }};
    }

    public TestResultState(IReplyCallback event, Character character, Skill skill, Characteristics characteristic) {
            skillValue = character.getCharacteristic(characteristic);
            skillValue += character.getSkillBonus(skill).bonus;
            throwResult = new Random().nextInt(100) + 1;
            Difficulties difficulty = Difficulties.getFromValue(skillValue - throwResult);
            event.reply(character.getName() + " tested for " + skill.getName() + " using " + characteristic.getName() + " and rolled " + throwResult + "\n" +
                    "This passes tests up to " + difficulty.getName() + " difficulty.")
                    .setComponents(getMessageComponents())
                    .queue();
            this.hook = event.getHook();
    }

    private String degreesOfSuccess(Difficulties difficulty){
        int modifiedSkillValue = skillValue + difficulty.getModifier();
        if (modifiedSkillValue >= throwResult) {
            int degrees = 1 + (modifiedSkillValue / 10) - (throwResult / 10);
            return degrees + " degrees of success";
        }
        int degrees = -1 + (modifiedSkillValue / 10) - (throwResult / 10);
        return -degrees + " degrees of failure";
    }

    @Override
    public ListenerState handleStringSelectEvent(StringSelectInteractionEvent event) throws UnexpectedEventException {
        switch (event.getComponentId()) {
            case difficultySelectId -> {
                Difficulties difficulty = Difficulties.getFromId(event.getValues().get(0));
                String degrees = degreesOfSuccess(difficulty);
                event.reply("With difficulty of " + difficulty.getName() + ", the character achieved " + degrees)
                        .setEphemeral(true)
                        .queue();
            }
            default ->
                    throw new UnexpectedEventException();
        }
        return this;

    }
}
