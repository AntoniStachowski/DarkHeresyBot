package org.example.listeners.createCharacter;

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
import org.example.character.CharacterManager;
import org.example.character.skills.SimpleSkills;
import org.example.character.skills.SkillBonuses;
import org.example.listeners.ListenerState;
import org.example.listeners.UnexpectedEventException;

import java.util.ArrayList;
import java.util.List;

public class SetSimpleSkillsState extends ListenerState {
    private static final String abortCreationName = "stop-creating";
    private static final String addSkillName = "add-skill";
    private static final String doneName = "done";
    private static final String skillSelectName = "skill-select";
    private static final String bonusSelectName = "bonus-select";
    private final Character character;
    private final InteractionHook hook;
    private final CharacterManager characterManager;


    private Skill skill;
    private SkillBonuses bonus;

    private static List<LayoutComponent> getMessageComponents(Skill chosenSkill, SkillBonuses chosenBonus, boolean readyToAdd){
        StringSelectMenu.Builder skillSelectionBuilder = StringSelectMenu.create(skillSelectName);
        for (SimpleSkills skill: SimpleSkills.values()) {
            skillSelectionBuilder.addOption(
                    skill.name, skill.name
            );
        }
        if (chosenSkill != null) {
            skillSelectionBuilder.setDefaultOptions(SelectOption.of(chosenSkill.getName(), chosenSkill.getName()));
        }
        StringSelectMenu.Builder selectionBuilder = StringSelectMenu.create(bonusSelectName);
        for (SkillBonuses bonuses: SkillBonuses.values()) {
            selectionBuilder.addOption(
                    bonuses.getName(), bonuses.getName()
            );
        }
        if (chosenBonus != null) {
            selectionBuilder.setDefaultOptions(SelectOption.of(chosenBonus.getName(), chosenBonus.getName()));
        }

        return new ArrayList<>(){{
            add(ActionRow.of(skillSelectionBuilder.build()));
            add(ActionRow.of(selectionBuilder.build()));
            add(ActionRow.of(Button.of(ButtonStyle.DANGER, abortCreationName, "Abort"),
                    Button.of(ButtonStyle.PRIMARY, addSkillName, "Add").withDisabled(!readyToAdd),
                    Button.of(ButtonStyle.SECONDARY, doneName, "Done")));
        }};
    }

    public SetSimpleSkillsState(IReplyCallback event, Character character, String message, CharacterManager characterManager) {
        this.character = character;
        this.characterManager = characterManager;
        String content = (message == null ? "" : message + '\n') +
                "Set your " +
                (message == null ? "" : "remaining ") +
                "skills. Once all are set, click 'Done'";
        event.reply(content)
                .setComponents(getMessageComponents(null, null, false))
                .setEphemeral(true)
                .queue();
        hook = event.getHook();
    }

    @Override
    public ListenerState handleStringSelectEvent(StringSelectInteractionEvent event) throws UnexpectedEventException {
        boolean matched = false;
        switch (event.getComponentId()) {
            case skillSelectName -> {
                skill = SimpleSkills.getFromName(event.getValues().get(0));
                matched = true;
            }
            case bonusSelectName -> {
                bonus = SkillBonuses.getByName(event.getValues().get(0));
                matched = true;
            }
        }
        if (matched) {
            boolean readyToAdd = skill != null && bonus != null;
            hook.editOriginalComponents(getMessageComponents(skill, bonus, readyToAdd)).queue();
            event.deferEdit().queue();
            return this;
        }
        throw new UnexpectedEventException();
    }

    @Override
    public ListenerState handleButtonEvent(ButtonInteractionEvent event) throws UnexpectedEventException {
        switch (event.getComponentId()) {
            case abortCreationName -> {
                hook.deleteOriginal().queue();
                event.reply("Creation cancelled.").setEphemeral(true).queue();
                return null;
            }
            case addSkillName -> {
                if (skill == null || bonus == null) {
                    throw new UnexpectedEventException();
                }
                character.setSkill(skill, bonus);
                hook.deleteOriginal().queue();
                return new SetSimpleSkillsState(event, character, "Set " + skill.getName() + " to " + bonus.getName() +"!", characterManager);
            }
            case doneName -> {
                return new SetComplexSkillsState(event, character, null, characterManager);
            }
        }
        throw new UnexpectedEventException();
    }
}
