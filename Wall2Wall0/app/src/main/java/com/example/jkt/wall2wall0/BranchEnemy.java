package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by James on 4/16/2015.
 */

// OBSOLETE CLASS
public class BranchEnemy extends FallingEnemy {


    public BranchEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0f, 1.5f);//5.5
        this.bounds = new Rectangle(x, y, width, height);
    }
}
