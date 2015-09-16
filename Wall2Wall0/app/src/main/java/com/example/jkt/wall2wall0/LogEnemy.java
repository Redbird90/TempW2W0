package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by James on 4/16/2015.
 */
public class LogEnemy extends FallingEnemy {

    public LogEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0.0f, 3.2f);//4.8,1.5
        this.bounds = new Rectangle(x+6, y+27, 17, 12);
        Rectangle bounds2 = new Rectangle(x + 27, y + 11, 24, 17);
        Rectangle bounds3 = new Rectangle(x + 59, y + 6, 16, 21);
        this.bounds_tsil.add(bounds);
        this.bounds_tsil.add(bounds2);
        this.bounds_tsil.add(bounds3);
    }

    public int getImageName() {
        // NOT NEEDED
        //return "LogEnemyhighres-100px.png"
        return 0;
    }
}
