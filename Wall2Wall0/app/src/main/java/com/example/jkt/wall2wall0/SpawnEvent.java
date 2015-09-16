package com.example.jkt.wall2wall0;

/**
 * Created by JDK on 4/21/2015.
 */
public class SpawnEvent {

    int enemy_type;
    int enemy_x_location;
    float enemy_spawn_time;

    // SpawnEvent class is used to hold all relevant attributes for each enemy spawn, this
    // information will be used to create FallingEnemy objects in GameScreen class
    public SpawnEvent(int type, int location, float time) {
        this.enemy_type = type;
        this.enemy_x_location = location;
        this.enemy_spawn_time = time;
    }

}
