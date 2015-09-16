package com.example.jkt.wall2wall0;

import android.util.Log;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by James on 4/16/2015.
 */
public class DroneEnemy extends FallingEnemy {

    private boolean start_moving_left;
    private int num_of_updates;
    private boolean stop_movement;
    private float prev_x_velocity;
    private int start_at_update;
    private int divisor;
    private boolean shakebool;

    public DroneEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0.5f, 3.5f);//(2.5,3)(0.5,1.5)

        this.bounds = new Rectangle(x + 22, y + 43, 52, 28);
        Rectangle bounds2 = new Rectangle(x + 11, y + 10, 74, 30);
        Rectangle bounds3 = new Rectangle(x + 2, y, 92, 5);
        this.bounds_tsil.add(bounds);
        this.bounds_tsil.add(bounds2);
        this.bounds_tsil.add(bounds3);

        this.divisor = 30;
        this.num_of_updates = 0;
        this.stop_movement = false;
    }

    @Override
    public void update_enemy() {
        // Keep track of updates to know when to stop movement
        Rectangle boundsrect = (Rectangle) bounds;
        Log.i("DroneEnemy", String.valueOf(this.num_of_updates));
        Log.i("DroneEnemy", String.valueOf(this.x_pos) + ", " + String.valueOf(this.y_pos));
        Log.i("DroneEnemy", String.valueOf(boundsrect.getLowerLeft().getX()) + ", " + String.valueOf(boundsrect.getLowerLeft().getY()));
        Log.i("DroneEnemy", String.valueOf(shakebool));
        this.num_of_updates += 1;

        // Keep track of changes in x and y positions to update bounds accordingly
        this.x_change = 0f;
        this.y_change = 0f;
        float old_y_pos = this.y_pos;
        float old_x_pos = this.x_pos;

        // Update positions based on velocities
        if (this.player_jumping) {
            this.y_pos += (velocity.getY() + 0.5f);//1.5
            this.x_pos += velocity.getX();
            //this.update_bounds();
        } else {
            this.y_pos += velocity.getY();
            this.x_pos += velocity.getX();
            //this.update_bounds();
        }

        // Change direction if an x velocity of +/- 3 is reached
        if (this.velocity.getX() == 3f) {
            this.start_moving_left = true;
        } else if (this.velocity.getX() == -3f) {
            this.start_moving_left = false;
        }

        // Update x velocity based on current x direction
        if (this.start_moving_left && !stop_movement) {
            float updated_x1 = this.velocity.getX() - 0.25f;
            this.velocity.set(updated_x1, this.velocity.getY());
        } else if (!this.start_moving_left && !stop_movement) {
            float updated_x2 = this.velocity.getX() + 0.25f;
            this.velocity.set(updated_x2, this.velocity.getY());
        }

        // Stop movement if (first stop: 30th frame, second on: 10th frame)
        // CHANGE WHEN ABLE TO TEST
        if (!stop_movement && this.num_of_updates % this.divisor == 0) {
            this.stop_movement = true;
            this.prev_x_velocity = this.velocity.getX();
            this.velocity.set(0.0f, 0.0f);
            this.start_at_update = this.num_of_updates + 20;
            this.divisor = this.start_at_update + 30 + 20;//added 20 so every 30th fram
        }

        // Start movement if stopped and 20th frame reached after stop
        if (this.stop_movement && (this.num_of_updates == this.start_at_update)) {
            this.stop_movement = false;
            this.velocity.set(prev_x_velocity, 3.5f);//3,1.5
        // Mimic shake if movement stopped
        } else if (this.stop_movement) {
            if (this.shakebool) {
                this.x_pos += 2.5f;//1
                this.shakebool = false;
            } else {
                this.x_pos -= 2.5f;//1
                this.shakebool = true;
            }
        }

        float new_y_pos = this.y_pos;
        float new_x_pos = this.x_pos;
        this.y_change = (new_y_pos - old_y_pos);
        this.x_change = (new_x_pos - old_x_pos);

        this.update_bounds();
        this.y_height_thresh_change = 0f;

        boundsrect = (Rectangle) bounds;
        Log.i("DroneEnemy", String.valueOf(this.x_pos) + ", " + String.valueOf(this.y_pos));
        Log.i("DroneEnemy", String.valueOf(boundsrect.getLowerLeft().getX()) + ", " + String.valueOf(boundsrect.getLowerLeft().getY()));
    }

    public int getImageName() {
        // NOT NEEDED
        return 0;
        //return "Factory_Dronehighres-95px.png";
    }
}
