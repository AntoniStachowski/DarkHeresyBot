package org.example.character.skills;

import org.example.Skill;
import org.example.character.Characteristics;

public enum SimpleSkills implements Skill {
    ACROBATICS("Acrobatics", Characteristics.AGILITY, Characteristics.STRENGTH),
    ATHLETICS("Athletics", Characteristics.STRENGTH, Characteristics.TOUGHNESS),
    AWARENESS("Awareness", Characteristics.PERCEPTION, Characteristics.FELLOWSHIP, Characteristics.INTELLIGENCE),
    CHARM("Charm", Characteristics.FELLOWSHIP, Characteristics.INFLUENCE),
    COMMAND("Command", Characteristics.FELLOWSHIP, Characteristics.INTELLIGENCE, Characteristics.STRENGTH, Characteristics.WILLPOWER),
    COMMERCE("Commerce", Characteristics.INTELLIGENCE, Characteristics.FELLOWSHIP),
    DECEIVE("Deceive", Characteristics.FELLOWSHIP, Characteristics.INTELLIGENCE),
    DODGE("Dodge", Characteristics.AGILITY),
    INQUIRY("Inquiry", Characteristics.FELLOWSHIP, Characteristics.INTELLIGENCE, Characteristics.PERCEPTION),
    INTERROGATION("Interrogation", Characteristics.WILLPOWER, Characteristics.FELLOWSHIP),
    INTIMIDATE("Intimidate", Characteristics.STRENGTH, Characteristics.WILLPOWER),
    LOGIC("Logic", Characteristics.INTELLIGENCE, Characteristics.AGILITY),
    MEDICAE("Medicae", Characteristics.INTELLIGENCE, Characteristics.AGILITY, Characteristics.PERCEPTION),
    PARRY("Parry", Characteristics.WEAPON_SKILL),
    PSYNISCIENCE("Psyniscience", Characteristics.PERCEPTION, Characteristics.WILLPOWER),
    SCRUTINY("Scrutiny", Characteristics.PERCEPTION, Characteristics.FELLOWSHIP),
    SECURITY("Security", Characteristics.INTELLIGENCE, Characteristics.AGILITY),
    SLEIGHT_OF_HAND("Sleight of hand", Characteristics.AGILITY, Characteristics.INTELLIGENCE),
    STEALTH("Stealth", Characteristics.AGILITY, Characteristics.PERCEPTION),
    SURVIVAL("Survival", Characteristics.PERCEPTION, Characteristics.AGILITY, Characteristics.INTELLIGENCE),
    TECH_USE("Tech-use", Characteristics.INTELLIGENCE, Characteristics.AGILITY);

    public final String name;
    public final Characteristics mainCharacteristic;
    public final Characteristics[] secondaryCharacteristics;

    SimpleSkills(String name, Characteristics mainCharacteristic, Characteristics... secondaryCharacteristics) {
        this.name = name;
        this.mainCharacteristic = mainCharacteristic;
        this.secondaryCharacteristics = secondaryCharacteristics;
    }

    public static SimpleSkills getFromName(String name){
        SimpleSkills[] skills = SimpleSkills.values();
        for (SimpleSkills skill: skills) {
            if (skill.getName().equals(name)){
                return skill;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSpecializationName() {
        return null;
    }


}
