package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

import java.util.Random;

/**
 * Created by James on 4/16/2015.
 */
public class AppleEnemy extends FallingEnemy {

    private final int apple_orientation;

    public AppleEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0f, 2.8f);//5.5,1.8,2.2
        this.bounds = new Rectangle(x, y, width, height);
        this.bounds_tsil.add(bounds);
        Random randGen = new Random();
        this.apple_orientation = randGen.nextInt(3);
    }

    public int getImageName() {
        return this.apple_orientation;
    }
}
