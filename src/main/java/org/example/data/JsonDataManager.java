package org.example.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.DarkHeresyAdapterFactory;
import org.example.character.CharacterData;

import java.io.*;

public class JsonDataManager implements DataManager{
    private final File jsonFile;
    private final Gson gson;

    public JsonDataManager(File file) {
        this.jsonFile = file;
        this.gson = new GsonBuilder().registerTypeAdapterFactory(new DarkHeresyAdapterFactory()).enableComplexMapKeySerialization().setPrettyPrinting().create();
    }

    @Override
    public void save(CharacterData manager) {
        try {
            try (FileWriter writer = new FileWriter(jsonFile)) {
                System.out.println(gson.toJson(manager));
                writer.write(gson.toJson(manager));
            }
        } catch (IOException ignored) {
        }
    }

    @Override
    public CharacterData load() {
        try {
            CharacterData data = gson.fromJson(new FileReader(jsonFile), CharacterData.class);
            if (data == null) {
                return new CharacterData();
            }
            return data;
        } catch (FileNotFoundException e) {
            return new CharacterData();
        }
    }


}
