package com.bombers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Platform implements Entity {

    private String textureName;
    private Vector2 position;
    private Rectangle hitbox;

    private float width;
    private float height;

    private AssetManager assetManager;

    public Platform(String textureName, Vector2 position, AssetManager assetManager) {
        this.textureName = textureName;
        this.position = position;
        this.assetManager = assetManager;

        // Get texture to retrieve width and height
        Texture texture = assetManager.get(textureName, Texture.class);
        this.width = texture.getWidth();
        this.height = texture.getHeight();

        this.hitbox = new Rectangle(position.x, position.y, width, height);
    }

    @Override
    public String getTextureName() {
        return textureName;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2 newPosition) {
        this.position = newPosition;
        hitbox.setPosition(position.x, position.y);
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
