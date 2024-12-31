package org.example.character;

import org.example.data.DataManager;

public class CharacterManager {
    CharacterData characterData;
    DataManager manager;

    public CharacterManager(DataManager dataManager) {
        this.manager = dataManager;
        this.characterData = dataManager.load();
        System.out.println("Dupa");
        System.out.println(characterData);
    }

    public void addCharacter(String userId, Character character, boolean switchActive) {
        characterData.addCharacter(userId, character, switchActive);
        manager.save(characterData);
    }

    public Character getCharacter(String userId) {
        return characterData.getActiveCharacter(userId);
    }
}
