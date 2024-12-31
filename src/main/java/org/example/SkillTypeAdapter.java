package org.example;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class SkillTypeAdapter extends TypeAdapter<Skill> {
    @Override
    public void write(JsonWriter jsonWriter, Skill skill) throws IOException {
        System.out.println("KUUUYRWAAAAA");
        System.out.println(skill.getName());
        jsonWriter.value(skill.getName());
    }

    @Override
    public Skill read(JsonReader jsonReader) throws IOException {
        String name = jsonReader.nextString();
        System.out.println(name);
        System.out.println(Skill.getFromName(name));
        return Skill.getFromName(name);
    }
}
