package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

public class GameObject {
    public final Vector2 position;
    //public final Rectangle bounds;
    
    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x,y);
        //this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
    }
}
