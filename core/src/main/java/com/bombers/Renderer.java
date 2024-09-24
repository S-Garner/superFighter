package com.bombers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import java.util.List;

public class Renderer {
    private SpriteBatch spriteBatch;
    private AssetManager assetManager;

    public Renderer(AssetManager assetManager) {
        this.spriteBatch = new SpriteBatch();
        this.assetManager = assetManager;
    }

    public void render(List<Entity> entities, OrthographicCamera camera) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        for (Entity entity : entities) {
            Texture texture = assetManager.get(entity.getTextureName(), Texture.class);
            spriteBatch.draw(texture, entity.getPosition().x, entity.getPosition().y);
        }
        spriteBatch.end();
    }

    public void dispose() {
        spriteBatch.dispose();
    }
}
