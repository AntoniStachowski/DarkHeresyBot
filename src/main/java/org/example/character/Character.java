package org.example.character;

import org.example.Skill;
import org.example.character.skills.SkillBonuses;

import java.util.HashMap;
import java.util.Map;

public class Character {
    String name;
    Map<Characteristics, Integer> characteristics = new HashMap<>();
    Map<Skill, SkillBonuses> skills = new HashMap<>();

    public Character(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {return name;}

    public void setCharacteristic(Characteristics characteristic, int value) {
        characteristics.put(characteristic, value);
    }

    public void setSkill(Skill skill, SkillBonuses bonus) {
        skills.put(skill, bonus);
    }

    public SkillBonuses getSkillBonus(Skill skill) {
        return skills.getOrDefault(skill, SkillBonuses.UNKNOWN);
    }

    public Integer getCharacteristic(Characteristics characteristic) {
        return characteristics.get(characteristic);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(name).append("\n");
        for(Map.Entry<Characteristics, Integer> entry: characteristics.entrySet()) {
            builder.append(entry.getKey().getName()).append(": ").append(entry.getValue()).append("\n");
        }
        builder.append("\n");
        for(Map.Entry<Skill, SkillBonuses> entry: skills.entrySet()) {
            builder.append(entry.getKey().getName()).append(": ").append(entry.getValue().getName()).append("\n");
        }
        return builder.toString();
    }
}
