package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

import java.util.Random;

/**
 * Created by James on 4/16/2015.
 */
public class HammerEnemy extends FallingEnemy {

    private final int hammer_orientation;
    private float updated_y_velocity;
    private boolean updated_accel1 = false;
    private boolean updated_accel2 = false;

    public HammerEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0f, 0f);//6.5
        this.accel = new Vector2(0f, 0.05f);
        Random randGen = new Random();
        this.hammer_orientation = randGen.nextInt(2);
        this.bounds = new Rectangle(x, y+80, 62, 20);
        Rectangle bounds2;
        if (this.hammer_orientation == 0) {
            bounds2 = new Rectangle(x+25, y, 33, 79);
        } else {
            bounds2 = new Rectangle(x+4, y, 33, 79);
        }
        this.bounds_tsil.add(this.bounds);
        this.bounds_tsil.add(bounds2);
    }

    @Override
    public void update_enemy() {
        // Override to implement acceleration in fall
        this.updated_y_velocity = this.velocity.getY() + this.accel.getY();
        this.velocity.set(this.velocity.getX(), this.updated_y_velocity);
        // Use parent's update method as normal with updated velocity
        super.update_enemy();
        // Check for height to update acceleration
        if (this.y_pos > 75 && !this.updated_accel1) {
            this.accel.set(0f, 0.06f);
            this.updated_accel1 = true;
        } else if (this.y_pos > 150 && !this.updated_accel2) {
            this.accel.set(0f, 0.8f);
            this.updated_accel2 = true;
        }
    }

    public int getImageName() {
        return this.hammer_orientation;

/*        if (this.hammer_orientation == 0) {
            return "Factory_Hammerhighres-100pxh.png";
        } else {
            return "Factory_Hammerhighres_reverse-100pxh.png";
        }*/
    }
}
