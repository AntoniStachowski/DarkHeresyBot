package org.example.listeners.createCharacter;

import org.example.character.Character;
import org.example.character.CharacterManager;
import org.example.listeners.StateContext;
import org.example.listeners.responses.ResponsePublisher;

public class CreateCharacterContext extends StateContext {
    private final CharacterManager characterManager;
    private Character character;


    protected CreateCharacterContext(ResponsePublisher publisher, CharacterManager characterManager) {
        super(publisher);
        this.characterManager = characterManager;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
