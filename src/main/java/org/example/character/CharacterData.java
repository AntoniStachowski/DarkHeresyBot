package org.example.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterData {
    private Integer characterCount = 0;
    private final Map<Integer, Character> characters = new HashMap<>();
    private final Map<String, List<Integer>> charactersByUser = new HashMap<>();
    private final Map<String, Integer> activeCharacters = new HashMap<>();

    public void addCharacter(String userId, Character character, boolean switchActive) {
        characters.put(characterCount, character);
        charactersByUser.putIfAbsent(userId, new ArrayList<>());
        charactersByUser.get(userId).add(characterCount);
        if (switchActive) {
            activeCharacters.put(userId, characterCount);
        }
        characterCount++;
    }

    public Character getActiveCharacter(String userId) {
        return characters.get(activeCharacters.get(userId));
    }

}
