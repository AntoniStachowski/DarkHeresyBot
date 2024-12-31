package org.example.character.skills;

import org.example.Skill;

public enum LinguisticTypes implements Skill {
    CHAPTER_RUNES("Chapter runes"),
    CHAOS_MARKS("Chaos marks"),
    ELDAR("Eldar"),
    HIGH_GOTHIC("High gothic"),
    IMPERIAL_CODES("Imperial codes"),
    LOW_GOTHIC("Low gothic"),
    MERCENARY_CANT("Mercenary cant"),
    NECRONTYR("Necrontyr"),
    ORK("Ork"),
    TECHNA_LINGUA("Techna lingua"),
    TAU("Tau"),
    UNDERWORLD("Underworld"),
    XENOS_MARKINGS("Xenos markings");

    private final String name;

    LinguisticTypes(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return ComplexSkills.LINGUISTICS.name + "("  + name + ")";
    }

    @Override
    public String getSpecializationName() {
        return name;
    }
}
