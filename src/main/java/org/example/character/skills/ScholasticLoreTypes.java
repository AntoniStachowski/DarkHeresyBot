package org.example.character.skills;

import org.example.Skill;

public enum ScholasticLoreTypes implements Skill {
    ASTROMANCY("Astromancy"),
    BEASTS("Beasts"),
    BUREAUCRACY("Bureaucracy"),
    CHYMISTRY("Chymistry"),
    CRYPTOLOGY("Cryptology"),
    HERALDRY("Heraldry"),
    IMPERIAL_WARRANTS("Imperial warrants"),
    JUDGEMENT("Judgement"),
    LEGEND("Legend"),
    NUMEROLOGY("Numerology"),
    OCCULT("Occult"),
    PHILOSOPHY("Philosophy"),
    TACTICA_IMPERIALIS("Tactica Imperialis");

    private final String name;

    ScholasticLoreTypes(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return ComplexSkills.SCHOLASTIC_LORE.name + "("  + name + ")";
    }

    @Override
    public String getSpecializationName() {
        return name;
    }
}
