package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Rectangle;

/**
 * Created by JDK on 5/7/2015.
 */

// WallHazard class is a subclass of Rectangle mainly used to hold x and y positions of hazard
// images and easily handle collisions in GameScreen class
public class WallHazard extends Rectangle {

    private final float x_pos;
    private float y_pos;

    public float getY_pos() {
        return y_pos;
    }

    public void addY_pos(float y) {
        this.y_pos += y;
        this.update();
    }

    public WallHazard(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.x_pos = x;
        this.y_pos = y;
    }

    public void update() {
        this.setLowerLeft(this.x_pos, this.y_pos);
    }
}
