package org.example.character.skills;

import org.example.Skill;

public enum ForbiddenLoreTypes implements Skill {
    ARCHAEOTECH("Archaeotech"),
    CHAOS_SPACE_MARINES("Chaos space marines"),
    CRIMINAL_CARTELS_AND_SMUGGLERS("Criminal cartels and smugglers"),
    DAEMONOLOGY("Daemonology"),
    HERESY("Heresy"),
    THE_HORUS_HERESY_AND_THE_LONG_WAR("The Horus Heresy and the Long War"),
    INQUISITION("Inquisition"),
    MUTANTS("Mutants"),
    OFFICIO_ASSASSINORUM("Officio Assassinorum"),
    PIRATES("Pirates"),
    PSYKERS("Psykers"),
    THE_WARP("The warp"),
    XENOS("Xenos");

    private final String name;

    ForbiddenLoreTypes(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return ComplexSkills.FORBIDDEN_LORE.name + "("  + name + ")";
    }

    @Override
    public String getSpecializationName() {
        return name;
    }
}
