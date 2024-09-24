package com.bombers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface Entity {
    String getTextureName();
    Vector2 getPosition();
    void setPosition(Vector2 position);
    Rectangle getHitbox();
    float getWidth();
    float getHeight();
}
