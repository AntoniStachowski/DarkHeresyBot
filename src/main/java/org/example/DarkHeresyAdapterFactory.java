package org.example;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.example.character.skills.SkillBonuses;

public class DarkHeresyAdapterFactory implements TypeAdapterFactory {
    private final TypeAdapter<Skill> skillAdapter = new SkillTypeAdapter();
    private final TypeAdapter<SkillBonuses> bonusesTypeAdapter = new SkillBonusAdapter();
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if(Skill.class.isAssignableFrom(typeToken.getRawType())) {
            return (TypeAdapter<T>) skillAdapter;
        } else if (SkillBonuses.class.isAssignableFrom(typeToken.getRawType())) {
            return (TypeAdapter<T>) bonusesTypeAdapter;
        }
        return null;
    }
}

