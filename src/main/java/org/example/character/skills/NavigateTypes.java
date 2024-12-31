package org.example.character.skills;

import org.example.Skill;

public enum NavigateTypes implements Skill {
    SURFACE("Surface"),
    STELLAR("Stellar"),
    WARP("War");

    private final String name;

    NavigateTypes(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return ComplexSkills.NAVIGATE.name + "("  + name + ")";
    }

    @Override
    public String getSpecializationName() {
        return name;
    }
}
