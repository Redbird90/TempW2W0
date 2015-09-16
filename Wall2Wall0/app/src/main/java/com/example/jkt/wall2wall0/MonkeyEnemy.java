package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by James on 4/16/2015.
 */
public class MonkeyEnemy extends FallingEnemy {


    public MonkeyEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        if (this.x_pos >= 195) {
            this.velocity = new Vector2(-1.25f, 3.75f);//(-2, 7)(-1,3.25)
        } else if (this.x_pos <= 195) {
            this.velocity = new Vector2(1.25f, 3.75f);//(2, 7)(-1,3.25)
        }
        this.bounds = new Rectangle(x, y, width, height);
        this.bounds_tsil.add(bounds);
    }

    public int getImageName() {
        if (this.velocity.getX() < 0) {
            return 0;
            //return "MonkeyEnemyhighres_reverse-90px.png";
        } else {
            return 1;
            //return "MonkeyEnemyhighres-90px.png";
        }
    }
}
