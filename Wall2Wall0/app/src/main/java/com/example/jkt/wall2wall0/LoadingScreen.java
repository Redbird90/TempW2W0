package com.example.jkt.wall2wall0;

import android.app.Activity;
import android.util.Log;

import com.example.jkt.wall2wall0.impl.AndroidFileIO;
import com.example.jkt.wall2wall0.impl.AndroidGame;

/**
 * Created by JDK on 4/1/2015.
 */
// OBSOLETE CLASS
public class LoadingScreen extends Screen {

    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();

/*        Assets.player_image = g.newImage("player_image.png", Graphics.ImageFormat.RGB565);
        Assets.enemy_image1 = g.newImage("enemy_image1.png", Graphics.ImageFormat.RGB565);
        Assets.enemy_image2 = g.newImage("enemy_image2.png", Graphics.ImageFormat.RGB565);
        Assets.enemy_image3 = g.newImage("enemy_image3.png", Graphics.ImageFormat.RGB565);*/

        //game.setScreen(new MainMenuScreen(game));
        Log.i("Loading Screen", "Setting new MainMenuScreen...");
    }

    @Override
    public void paint(float deltaTime) {

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    //@Override
    public void backButton() {
        // TODO Auto-generated method stub

    }

}
