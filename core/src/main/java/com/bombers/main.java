package com.bombers;

import com.badlogic.gdx.Game;

public class main extends Game {

    @Override
    public void create() {
        // Set the initial screen to MainGameScreen
        setScreen(new MainGameScreen());
    }

    @Override
    public void render() {
        // Delegate the render method to the current screen
        super.render();
    }

    @Override
    public void dispose() {
        // Dispose of the current screen and any other resources
        super.dispose();
    }
}
