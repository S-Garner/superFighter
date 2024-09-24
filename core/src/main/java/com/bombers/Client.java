package com.bombers;

import com.badlogic.gdx.Gdx;

public class Client {

    private String playerName;
    private int[] controlKeys; // [Left, Right, Up, Down]
    private Character character;

    public Client(String playerName, int[] controlKeys, Character character) {
        this.playerName = playerName;
        this.controlKeys = controlKeys;
        this.character = character;
    }

    public void handleInput() {
        // Move Left
        if (Gdx.input.isKeyPressed(controlKeys[0])) {
            character.setMoveLeft(true);
        } else {
            character.setMoveLeft(false);
        }

        // Move Right
        if (Gdx.input.isKeyPressed(controlKeys[1])) {
            character.setMoveRight(true);
        } else {
            character.setMoveRight(false);
        }

        // Move Up
        if (Gdx.input.isKeyPressed(controlKeys[2])) {
            character.setMoveUp(true);
        } else {
            character.setMoveUp(false);
        }

        // Move Down
        if (Gdx.input.isKeyPressed(controlKeys[3])) {
            character.setMoveDown(true);
        } else {
            character.setMoveDown(false);
        }
    }

    public Character getCharacter() {
        return character;
    }
}
