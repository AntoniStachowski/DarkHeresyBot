package org.example;

import org.example.character.skills.ComplexSkills;
import org.example.character.skills.SimpleSkills;

public interface Skill {
    String getName();

    String getSpecializationName();

    static Skill getFromName(String name){
        Skill simpleSkill = SimpleSkills.getFromName(name);
        if (simpleSkill != null) {
            return simpleSkill;
        }
        return ComplexSkills.getSpecializedSkillFromName(name);
    }
}
