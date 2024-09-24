package com.bombers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;

public class MainGameScreen implements Screen {

    // Camera and viewport variables
    private OrthographicCamera camera;
    private FitViewport viewport;

    private int width = 720;
    private int height = 540;

    // Collection of all entities in the game
    private List<Entity> entities;

    // Collection of clients (players)
    private List<Client> clients;

    // Renderer for rendering entities
    private Renderer renderer;

    // AssetManager for managing assets
    private AssetManager assetManager;

    public MainGameScreen() {
        // Initialize the camera with an orthographic projection for 2D
        camera = new OrthographicCamera();

        // Set up the viewport with a virtual width and height
        viewport = new FitViewport(width, height, camera);

        // Center the camera
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        // Initialize collections
        entities = new ArrayList<>();
        clients = new ArrayList<>();

        // Initialize AssetManager
        assetManager = new AssetManager();

        // Initialize Renderer
        renderer = new Renderer(assetManager);

        // Load assets
        loadAssets();

        // Initialize game entities and clients
        initializeGame();
    }

    private void loadAssets() {
        // Load textures and other assets
        assetManager.load("guy1.png", Texture.class);
        assetManager.load("stickGuy2.png", Texture.class);
        //assetManager.load("libgdx.png", Texture.class);
        // Load other assets as needed
        assetManager.finishLoading();
    }

    private void initializeGame() {
        // Control keys for Player 1 (WASD)
        int[] player1Keys = new int[]{
            Input.Keys.A,      // Move Left
            Input.Keys.D,      // Move Right
            Input.Keys.W,      // Move Up
            Input.Keys.S       // Move Down
        };

        // Control keys for Player 2 (Arrow Keys)
        int[] player2Keys = new int[]{
            Input.Keys.LEFT,   // Move Left
            Input.Keys.RIGHT,  // Move Right
            Input.Keys.UP,     // Move Up
            Input.Keys.DOWN    // Move Down
        };

        // Create characters with initial positions using available textures
        Character character1 = new Character("guy1.png", new Vector2(100, 100), assetManager);
        Character character2 = new Character("stickGuy2.png", new Vector2(200, 100), assetManager);

        // Create clients (players) and assign their characters and control keys
        Client client1 = new Client("Player1", player1Keys, character1);
        Client client2 = new Client("Player2", player2Keys, character2);

        clients.add(client1);
        clients.add(client2);

        // Add characters to entities
        entities.add(character1);
        entities.add(character2);

        // Example of adding a platform using an available texture
        //Platform platform = new Platform("libgdx.png", new Vector2(0, 0), assetManager);
        //entities.add(platform);
    }


    @Override
    public void show() {
        // Initialize resources if needed
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);

        // Update the camera matrices
        camera.update();

        // Handle inputs for clients
        for (Client client : clients) {
            client.handleInput();
        }

        // Update game state
        updateGameState(delta);

        // Render the game using the Renderer
        renderer.render(entities, camera);
    }

    private void updateGameState(float delta) {
        // Update game logic here
        // For example, move characters based on inputs
        for (Entity entity : entities) {
            if (entity instanceof Character) {
                Character character = (Character) entity;
                character.update(delta);
                clampCharacterPosition(character);
            }
            // Handle other entity updates if needed
        }

        // Handle collisions, etc.
    }

    private void clampCharacterPosition(Character character) {
        float x = Math.max(0, Math.min(character.getPosition().x, viewport.getWorldWidth() - character.getWidth()));
        float y = Math.max(0, Math.min(character.getPosition().y, viewport.getWorldHeight() - character.getHeight()));
        character.setPosition(new Vector2(x, y));
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport dimensions when the window size changes
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        // Handle actions when the game is paused
    }

    @Override
    public void resume() {
        // Handle actions when the game is resumed
    }

    @Override
    public void hide() {
        // Release resources when the screen is no longer displayed
    }

    @Override
    public void dispose() {
        // Dispose of assets and resources
        renderer.dispose();
        assetManager.dispose();
    }
}
