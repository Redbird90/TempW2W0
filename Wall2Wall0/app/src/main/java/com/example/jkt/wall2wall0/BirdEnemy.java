package com.example.jkt.wall2wall0;

import android.util.Log;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Shape;
import com.example.jkt.wall2wall0.math.Vector2;

import java.util.ArrayList;

/**
 * Created by James on 4/16/2015.
 */
public class BirdEnemy extends FallingEnemy {
    private boolean start_moving_left;
    private boolean updated_velocity = false;
    private ArrayList<Shape> bounds_right_tsil = new ArrayList<>();
    private ArrayList<Shape> bounds_left_tsil = new ArrayList<>();

    public BirdEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(2.0f, 1.0f);//(4, 4),

        // Create bounds for reversed and normal bird images
        Rectangle bounds_right = new Rectangle(x + 2, y + 2, 10, 31);
        Rectangle bounds2_right = new Rectangle(x + 40, y + 4, 12, 63);
        Rectangle bounds3_right = new Rectangle(x + 14, y + 23, 24, 48);
        Rectangle bounds4_right = new Rectangle(x + 31, y + 74, 15, 12);
        Rectangle bounds5_right = new Rectangle(x + 6, y + 76, 24, 21);

        Rectangle bounds_left = new Rectangle(x + 45, y + 2, 10, 31);
        Rectangle bounds2_left = new Rectangle(x + 5, y + 4, 12, 63);
        Rectangle bounds3_left = new Rectangle(x + 19, y + 23, 24, 48);
        Rectangle bounds4_left = new Rectangle(x + 11, y + 74, 15, 12);
        Rectangle bounds5_left = new Rectangle(x + 27, y + 76, 24, 21);

        this.bounds_right_tsil.add(bounds_right);
        this.bounds_right_tsil.add(bounds2_right);
        this.bounds_right_tsil.add(bounds3_right);
        this.bounds_right_tsil.add(bounds4_right);
        this.bounds_right_tsil.add(bounds5_right);

        this.bounds_left_tsil.add(bounds_left);
        this.bounds_left_tsil.add(bounds2_left);
        this.bounds_left_tsil.add(bounds3_left);
        this.bounds_left_tsil.add(bounds4_left);
        this.bounds_left_tsil.add(bounds5_left);

        this.bounds_tsil = this.bounds_right_tsil;
        this.start_moving_left = false;
    }

    @Override
    public void update_enemy() {
        //Log.i("BirdEnemy", String.valueOf(this.x_pos + "," + this.y_pos));
        // Keep track of changes in x and y positions to update bounds accordingly
        this.x_change = 0f;
        this.y_change = 0f;
        float old_y_pos = this.y_pos;
        float old_x_pos = this.x_pos;

        // Update positions based on velocities
        if (this.player_jumping) {
            this.y_pos += (velocity.getY() + 0.5f);//1.5, CR
        } else {
            this.y_pos += velocity.getY();
        }
        this.x_pos += velocity.getX();

        // Possible changes to x and y positions done, assign appropriate variables
        float new_y_pos = this.y_pos;
        float new_x_pos = this.x_pos;
        this.y_change = (new_y_pos - old_y_pos);
        this.x_change = (new_x_pos - old_x_pos);

        //Log.i("BirdEnemy", String.valueOf(this.x_pos + "," + this.y_pos));
        //Log.i("BirdEnemy", String.valueOf(this.x_change+","+this.y_change));

        this.update_bounds();
        this.y_height_thresh_change = 0f;

        // Handle updating of x velocity
        //Log.i("BirdEnemyCheck", String.valueOf(this.velocity.getX()));
        if (this.velocity.getX() >= 3.0f) {//4
            this.start_moving_left = true;
        } else if (this.velocity.getX() <= -3.0f) {//-4
            this.start_moving_left = false;
        }
        if (this.start_moving_left) {
            float updated_x1 = this.velocity.getX() - 0.1f;
            this.velocity.set(updated_x1, this.velocity.getY());
        } else if (!this.start_moving_left) {
            float updated_x2 = this.velocity.getX() + 0.1f;
            this.velocity.set(updated_x2, this.velocity.getY());
        }

        // Handle updating of y velocity
        if (this.y_pos > 100f && !updated_velocity) {
            this.velocity.set(this.velocity.getX(), 3.8f);
            this.updated_velocity = true;
        }

        // Update bounds_tsil based on image blitted
        if (this.velocity.getX() >= 0) {
            this.bounds_tsil = this.bounds_right_tsil;
            // BirdEnemy is moving left
        } else {
            this.bounds_tsil = this.bounds_left_tsil;
        }
    }

    @Override
    public void update_bounds() {

        for (int i=0; i < this.bounds_left_tsil.size(); i++) {
            Rectangle curr_rect;
            curr_rect = (Rectangle) this.bounds_left_tsil.get(i);
            this.bounds_left_tsil.get(i).setLowerLeft(curr_rect.getLowerLeft().getX() + this.x_change,
                    curr_rect.getLowerLeft().getY() + this.y_change + this.y_height_thresh_change);
        }
        for (int i=0; i < this.bounds_right_tsil.size(); i++) {
            Rectangle curr_rect;
            curr_rect = (Rectangle) this.bounds_right_tsil.get(i);
            this.bounds_right_tsil.get(i).setLowerLeft(curr_rect.getLowerLeft().getX() + this.x_change,
                    curr_rect.getLowerLeft().getY() + this.y_change + this.y_height_thresh_change);
        }
    }

    public int getImageName() {
        // BirdEnemy is moving right
        if (this.velocity.getX() >= 0) {
            return 0;
            //return "BirdEnemyhighres_reverse.png";
            // BirdEnemy is moving left
        } else {
            return 1;
            //return "BirdEnemyhighres.png";
        }
    }
}