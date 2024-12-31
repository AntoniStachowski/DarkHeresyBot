package org.example.character.skills;

import org.example.Skill;

public enum OperateTypes implements Skill {
    SURFACE("Surface"),
    AERONAUTICA("Aeronautica"),
    VOIDSHIP("Voidship");

    private final String name;

    OperateTypes(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return ComplexSkills.OPERATE.name + "("  + name + ")";
    }

    @Override
    public String getSpecializationName() {
        return name;
    }
}
