package com.bombers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Character implements Entity {

    private String textureName;
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle hitbox;

    // Movement flags
    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;

    private float speed = 200f;          // Maximum horizontal speed (units per second)
    private float acceleration = 800f;   // Acceleration rate (units per second squared)
    private float deceleration = 800f;   // Deceleration rate (units per second squared)

    private float jumpVelocity = 350f;   // Initial jump velocity (units per second)
    private float gravity = 1000f;       // Gravity force (units per second squared)

    private boolean isOnGround = true;   // Flag to check if the character is on the ground

    private float width;
    private float height;

    private AssetManager assetManager;

    public Character(String textureName, Vector2 initialPosition, AssetManager assetManager) {
        this.textureName = textureName;
        this.position = initialPosition;
        this.velocity = new Vector2(0, 0);
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

    public void update(float deltaTime) {
        // Handle horizontal movement
        if (moveLeft) {
            velocity.x -= acceleration * deltaTime;
            if (velocity.x < -speed) {
                velocity.x = -speed;
            }
        }

        if (moveRight) {
            velocity.x += acceleration * deltaTime;
            if (velocity.x > speed) {
                velocity.x = speed;
            }
        }

        // Apply deceleration when no horizontal movement keys are pressed
        if (!moveLeft && !moveRight) {
            if (velocity.x > 0) {
                velocity.x -= deceleration * deltaTime;
                if (velocity.x < 0) velocity.x = 0;
            } else if (velocity.x < 0) {
                velocity.x += deceleration * deltaTime;
                if (velocity.x > 0) velocity.x = 0;
            }
        }

        // Handle jumping
        if (moveUp && isOnGround) {
            velocity.y = jumpVelocity;
            isOnGround = false;
        }

        // Apply gravity
        velocity.y -= gravity * deltaTime;

        // Update position based on velocity
        position.mulAdd(velocity, deltaTime);

        // Simple ground collision detection (assuming ground is at y = 0)
        if (position.y <= 0) {
            position.y = 0;
            velocity.y = 0;
            isOnGround = true;
        }

        // Update hitbox position
        hitbox.setPosition(position.x, position.y);
    }

    // Getters and setters for movement flags
    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
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
