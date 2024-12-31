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
import org.example.character.skills.ComplexSkills;
import org.example.character.skills.SkillBonuses;
import org.example.listeners.ListenerState;
import org.example.listeners.UnexpectedEventException;

import java.util.ArrayList;
import java.util.List;

public class SetComplexSkillsState extends ListenerState {
    private static final String abortCreationName = "stop-creating";
    private static final String addSkillName = "add-skill";
    private static final String doneName = "done";
    private static final String skillSelectName = "skill-select";

    private static final String specializationSelectName = "spec-select";
    private static final String bonusSelectName = "bonus-select";
    private final Character character;
    private final InteractionHook hook;
    private final CharacterManager characterManager;

    private ComplexSkills complexSkill;
    private Skill skillSpecialization;
    private SkillBonuses bonus;

    private List<LayoutComponent> getMessageComponents(){
        boolean readyToAdd = complexSkill != null && skillSpecialization != null && bonus != null;
        StringSelectMenu.Builder skillSelectionBuilder = StringSelectMenu.create(skillSelectName);
        StringSelectMenu.Builder specializationSelectionBuilder = StringSelectMenu.create(specializationSelectName);
        StringSelectMenu.Builder bonusSelectionBuilder = StringSelectMenu.create(bonusSelectName);
        for (ComplexSkills skill: ComplexSkills.values()) {
            skillSelectionBuilder.addOption(
                    skill.name, skill.name
            );
        }
        if (complexSkill != null) {
            skillSelectionBuilder.setDefaultOptions(SelectOption.of(complexSkill.name, complexSkill.name));
            List<Skill> specializations = complexSkill.getSubtypes();
            for (Skill specialization: specializations) {
                specializationSelectionBuilder.addOption(
                        specialization.getSpecializationName(), specialization.getSpecializationName()
                );
                if (specialization == skillSpecialization) {
                    specializationSelectionBuilder.setDefaultOptions(
                            SelectOption.of(specialization.getSpecializationName(), specialization.getSpecializationName())
                    );
                }
            }
        } else {
            specializationSelectionBuilder.addOption(
                    "Please select a skill first", "invalid"
            );
            specializationSelectionBuilder.setDisabled(true);
        }
        for (SkillBonuses bonuses: SkillBonuses.values()) {
            bonusSelectionBuilder.addOption(
                    bonuses.getName(), bonuses.getName()
            );
        }
        if (bonus != null) {
            bonusSelectionBuilder.setDefaultOptions(SelectOption.of(bonus.getName(), bonus.getName()));
        }

        return new ArrayList<>(){{
            add(ActionRow.of(skillSelectionBuilder.build()));
            add(ActionRow.of(specializationSelectionBuilder.build()));
            add(ActionRow.of(bonusSelectionBuilder.build()));
            add(ActionRow.of(Button.of(ButtonStyle.DANGER, abortCreationName, "Abort"),
                    Button.of(ButtonStyle.PRIMARY, addSkillName, "Add").withDisabled(!readyToAdd),
                    Button.of(ButtonStyle.SECONDARY, doneName, "Done")));
        }};
    }

    public SetComplexSkillsState(IReplyCallback event, Character character, String message, CharacterManager characterManager) {
        this.character = character;
        this.characterManager = characterManager;
        String content = (message == null ? "" : message + '\n') +
                "Set your " +
                (message == null ? "" : "remaining ") +
                "specialized skills. Once all are set, click 'Done'";
        event.reply(content)
                .setComponents(getMessageComponents())
                .setEphemeral(true)
                .queue();
        hook = event.getHook();
    }

    @Override
    public ListenerState handleStringSelectEvent(StringSelectInteractionEvent event) throws UnexpectedEventException {
        switch (event.getComponentId()) {
            case skillSelectName -> {
                ComplexSkills newComplexSkill = ComplexSkills.getFromName(event.getValues().get(0));
                if (complexSkill != newComplexSkill) {
                    skillSpecialization = null;
                }
                complexSkill = newComplexSkill;
            }
            case specializationSelectName -> {
                skillSpecialization = ComplexSkills.getSpecializationFromName(event.getValues().get(0));
            }
            case bonusSelectName -> {
                bonus = SkillBonuses.getByName(event.getValues().get(0));
            }
            default ->
                throw new UnexpectedEventException();
        }
        hook.editOriginalComponents(getMessageComponents()).queue();
        event.deferEdit().queue();
        return this;
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
                if (skillSpecialization == null || bonus == null) {
                    throw new UnexpectedEventException();
                }
                character.setSkill(skillSpecialization, bonus);
                hook.deleteOriginal().queue();
                return new SetComplexSkillsState(event, character, "Set " + skillSpecialization.getName() + " to " + bonus.getName() +"!", characterManager);
            }
            case doneName -> {
                hook.deleteOriginal().queue();
                event.reply("Character created! Here is a summary:\n" + character.toString()).queue();
                characterManager.addCharacter(event.getUser().getId(), character, true);
                return null;
            }
        }
        throw new UnexpectedEventException();
    }
}
