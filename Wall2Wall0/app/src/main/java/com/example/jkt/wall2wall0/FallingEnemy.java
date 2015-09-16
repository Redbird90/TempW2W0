package com.example.jkt.wall2wall0;

/**
 * Created by JDK on 3/29/2015.
 */

import android.util.Log;

import com.example.jkt.wall2wall0.math.Circle;
import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Shape;
import com.example.jkt.wall2wall0.math.Vector2;

import java.util.ArrayList;

/**         Dyn constructor:
 *         velocity = new Vector2();
             accel = new Vector2();
             GameObj constructor:
             this.position = new Vector2(x,y);
             this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
 */

public class FallingEnemy extends DynamicGameObject {

    public float x_pos;
    public float y_pos;
    public float width;
    public float height;
    private final int enemy_num;
    public ArrayList<Shape> bounds_tsil = new ArrayList<>();
    public Shape bounds;

    public boolean player_jumping = false;
    public float y_change;
    public float x_change;
    public float y_height_thresh_change;

    public FallingEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height);
        this.enemy_num = enemy_num;
        this.x_pos = x;
        this.y_pos = y;
        this.y_height_thresh_change = 0.0f;
    }

    public void update_enemy() {

        // Keep track of changes in x and y positions to update bounds accordingly
        this.x_change = 0f;
        this.y_change = 0f;
        float old_y_pos = this.y_pos;
        float old_x_pos = this.x_pos;

        // Update positions based on velocities
        if (this.player_jumping) {
            this.y_pos += (this.velocity.getY() + 0.5f);//1.5 THIS IS TOO SPEED UP ENEMIES WHEN
            // PLAYER IS IN AIR, CONSIDER REMOVAL
            this.x_pos += this.velocity.getX();
        } else {
            this.y_pos += this.velocity.getY();
            this.x_pos += this.velocity.getX();
        }

        // Possible changes to x and y positions done, assign appropriate variables
        float new_y_pos = this.y_pos;
        float new_x_pos = this.x_pos;
        this.y_change = (new_y_pos - old_y_pos);
        this.x_change = (new_x_pos - old_x_pos);

        this.update_bounds();
        this.y_height_thresh_change = 0f;
    }

    public int getEnemy_num() {
        return this.enemy_num;
    }

    public void update_bounds() {
        for (int i=0; i < this.bounds_tsil.size(); i++) {
            Rectangle curr_rect;
            curr_rect = (Rectangle) this.bounds_tsil.get(i);
            //Log.i("fallingEnemyD", String.valueOf(this.x_change) + ", " + String.valueOf(this.y_change));
            //Log.i("fallingEnemyD", String.valueOf(this.y_height_thresh_change));
            //Log.i("fallingEnemyBounds1", String.valueOf(curr_rect.getLowerLeft().getX() + ", " + String.valueOf(curr_rect.getLowerLeft().getY())));
            this.bounds_tsil.get(i).setLowerLeft(curr_rect.getLowerLeft().getX() + this.x_change,
                    curr_rect.getLowerLeft().getY() + this.y_change + this.y_height_thresh_change);
            //Log.i("fallingEnemyBounds2", String.valueOf(curr_rect.getLowerLeft().getX() + ", " + String.valueOf(curr_rect.getLowerLeft().getY())));
        }
    }

    public void setY_height_thresh_change(float y_height_thresh_change) {
        //Log.i("fallingEnemyD", "ythresh set as " + String.valueOf(y_height_thresh_change));
        this.y_height_thresh_change = y_height_thresh_change;
    }

    public void setPlayer_jumping(boolean player_jumping) {
        this.player_jumping = player_jumping;
    }

    public float getX_pos() {
        return this.x_pos;
    }

    public void setX_pos(float x_pos) {
        this.x_pos = x_pos;
    }

    public float getY_pos() {
        return this.y_pos;
    }

    public void setY_pos(float y_pos) {
        this.y_pos = y_pos;
    }

}
