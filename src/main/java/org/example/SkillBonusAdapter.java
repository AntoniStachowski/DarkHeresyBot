package org.example;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.character.skills.SkillBonuses;

import java.io.IOException;

public class SkillBonusAdapter extends TypeAdapter<SkillBonuses> {
    @Override
    public void write(JsonWriter jsonWriter, SkillBonuses skillBonus) throws IOException {
        System.out.println(skillBonus.getName());
        jsonWriter.jsonValue(skillBonus.getName());
    }

    @Override
    public SkillBonuses read(JsonReader jsonReader) throws IOException {
        String name = jsonReader.nextString();
        System.out.println(name);
        return SkillBonuses.getByName(name);
    }
}
