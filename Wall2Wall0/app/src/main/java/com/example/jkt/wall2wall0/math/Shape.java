package com.example.jkt.wall2wall0.math;

/**
 * Created by JDK on 4/24/2015.
 */
public class Shape {

    public Vector2 lowerLeft;
    public float x;
    public float y;

    public Shape(float x, float y) {
        this.x = x;
        this.y = y;
        this.lowerLeft = new Vector2(x, y);
    }

    public void setLowerLeft(float x, float y) {

    }

    public void setCenter(float x, float y) {

    }
}
