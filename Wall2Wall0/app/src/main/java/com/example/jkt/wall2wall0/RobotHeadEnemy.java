package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

import java.util.Random;

/**
 * Created by James on 4/16/2015.
 */
public class RobotHeadEnemy extends FallingEnemy {

    private final int head_orientation;

    public RobotHeadEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0f, 5f);//8
        Random randGen = new Random();
        this.head_orientation = randGen.nextInt(2);
        if (this.head_orientation == 0) {
            this.bounds = new Rectangle(x+7, y, 45, 57);
        } else {
            this.bounds = new Rectangle(x+13, y, 45, 57);
        }
        Rectangle bounds2 = new Rectangle(x, y + 15, 65, 6);
        this.bounds_tsil.add(this.bounds);
        this.bounds_tsil.add(bounds2);
    }

    public int getImageName() {
        return this.head_orientation;

/*        if (this.head_orientation == 0) {
            return "Factory_RobotHeadhighres-60px.png";
        } else {
            return "Factory_RobotHeadhighres_reverse-60px.png";
        }*/

    }
}
