package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Vector2;

public class DynamicGameObject extends GameObject {
    public Vector2 velocity;
    public Vector2 accel;
    
    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }

    // Use method to get the correct image to blit
    public int getImageName() {
        return 0;
    }
}
