package org.example.character.skills;

import org.example.Skill;
import org.example.character.Characteristics;

import java.util.Arrays;
import java.util.List;

public enum ComplexSkills {
    COMMON_LORE(CommonLoreTypes.class, "Common lore", Characteristics.INTELLIGENCE, Characteristics.FELLOWSHIP),
    FORBIDDEN_LORE(ForbiddenLoreTypes.class, "Forbidden lore", Characteristics.INTELLIGENCE, Characteristics.FELLOWSHIP),
    LINGUISTICS(LinguisticTypes.class, "Linguistics", Characteristics.INTELLIGENCE, Characteristics.FELLOWSHIP),
    NAVIGATE(NavigateTypes.class, "Navigate", Characteristics.INTELLIGENCE, Characteristics.PERCEPTION),
    OPERATE(OperateTypes.class, "Operate", Characteristics.AGILITY, Characteristics.INTELLIGENCE),
    SCHOLASTIC_LORE(ScholasticLoreTypes.class, "Scholastic lore", Characteristics.INTELLIGENCE, Characteristics.FELLOWSHIP),
    TRADE(TradeTypes.class, "Trade", Characteristics.INTELLIGENCE, Characteristics.AGILITY, Characteristics.FELLOWSHIP);

    private final Class<? extends Skill> subtypes;
    public final String name;
    public final Characteristics mainCharacteristic;
    public final Characteristics[] secondaryCharacteristics;

    ComplexSkills(Class<? extends Skill> subtypes, String name, Characteristics mainCharacteristic, Characteristics... secondaryCharacteristics){
        this.subtypes = subtypes;
        this.name = name;
        this.mainCharacteristic = mainCharacteristic;
        this.secondaryCharacteristics = secondaryCharacteristics;
    }

    public List<Skill> getSubtypes() {
        return Arrays.asList(subtypes.getEnumConstants());
    }

    public static ComplexSkills getFromName(String name) {
        ComplexSkills[] complexSkills = ComplexSkills.values();
        for (ComplexSkills complexSkill: complexSkills) {
            if (complexSkill.name.equals(name)) {
                return complexSkill;
            }
        }
        return null;
    }

    public static Skill getSpecializedSkillFromName(String name) {
        ComplexSkills[] complexSkills = ComplexSkills.values();
        for (ComplexSkills complexSkill: complexSkills) {
            List<Skill> specializations = complexSkill.getSubtypes();
            for (Skill specialization: specializations) {
                if (specialization.getName().equals(name)){
                    return specialization;
                }
            }
        }
        return null;
    }

    public static Skill getSpecializationFromName(String name) {
        ComplexSkills[] complexSkills = ComplexSkills.values();
        for (ComplexSkills complexSkill: complexSkills) {
            List<Skill> specializations = complexSkill.getSubtypes();
            for (Skill specialization: specializations) {
                if (specialization.getSpecializationName().equals(name)){
                    return specialization;
                }
            }
        }
        return null;
    }



}

