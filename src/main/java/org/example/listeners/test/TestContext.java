package org.example.listeners.test;

import org.example.character.Character;
import org.example.listeners.StateContext;
import org.example.listeners.responses.ResponsePublisher;

public class TestContext extends StateContext {
    private final Character character;

    protected TestContext(ResponsePublisher publisher, Character character) {
        super(publisher);
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }
}
