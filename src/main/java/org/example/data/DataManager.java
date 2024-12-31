package org.example.data;

import org.example.character.CharacterData;

public interface DataManager {
    void save(CharacterData manager);

    CharacterData load();
}
