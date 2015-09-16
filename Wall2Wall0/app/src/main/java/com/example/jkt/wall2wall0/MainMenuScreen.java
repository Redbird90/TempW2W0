package com.example.jkt.wall2wall0;

import android.app.Activity;
import android.graphics.*;
import android.graphics.Color;
import android.util.Log;

import com.example.jkt.wall2wall0.impl.AndroidGame;

import java.util.List;

/**
 * Created by JDK on 4/3/2015.
 */
public class MainMenuScreen extends Screen {

    private final Image mainMenuSplash;
    private final Image sound_enabled_img;
    private final Image sound_disabled_img;
    private boolean at_settings;
    private boolean at_leaderboards;

    public MainMenuScreen(Game game) {
        super(game);
        Log.i("MainMenuScreen", "Starting Main Menu Screen...");
        Graphics g = game.getGraphics();
        this.mainMenuSplash = g.newImage("SplashScreenhighresfilled.png", Graphics.ImageFormat.ARGB8888);
        this.sound_enabled_img = g.newImage("UI-SettingsEnabledWindowhighres.png", Graphics.ImageFormat.ARGB8888);
        this.sound_disabled_img = g.newImage("UI-SettingsDisabledWindowhighres.png", Graphics.ImageFormat.ARGB8888);
        //Music menu_music = game.getAudio().newMusic("Boxing Bell Start Round.wav");
        //menu_music.play();
        //menu_music.setLooping(true);

    }

    Paint menu_paint = new Paint();

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int touchEventsListSize = touchEvents.size();
        Log.i("MainMenuScreen", "size is " + String.valueOf(touchEventsListSize));
        // TODO: Keep or remove sync below
        synchronized (touchEvents) {
            // removed toucheventslistsize, used size() instead
            for (int touchEventIndex = 0; touchEventIndex < touchEvents.size(); touchEventIndex++) {
                Input.TouchEvent event = touchEvents.get(touchEventIndex);
                if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                    // If PLAY button pressed, start game
                    if (!at_settings && inBounds(event, 95, 468, 240, 76)) {
                        game.setScreen(new GameScreen(game));
                        return;
                    }
                    // If SETTINGS button pressed, update at_settings to draw Settings window
                    int SETTINGS_CHANGE_X_POSITION = 94;
                    int SETTINGS_CHANGE_Y_POSITION = 418;
                    int SETTINGS_CHANGE_WIDTH = 294;
                    int SETTINGS_CHANGE_HEIGHT = 68;
                    int SETTINGS_BACK_X_POSITION = 192;
                    int SETTINGS_BACK_Y_POSITION = 522;
                    int SETTINGS_BACK_WIDTH = 88;
                    int SETTINGS_BACK_HEIGHT = 66;
                    if (!at_settings && inBounds(event, 95, 680, 290, 76)) {
                        at_settings = true;
                        // If Settings window open, check for button press to enable/disable sound and
                        // update Settings variable
                    } else if (at_settings && Settings.soundEnabled && inBounds(event, SETTINGS_CHANGE_X_POSITION, SETTINGS_CHANGE_Y_POSITION, SETTINGS_CHANGE_WIDTH, SETTINGS_CHANGE_HEIGHT)) {
                        Settings.soundEnabled = false;
                    } else if (at_settings && !Settings.soundEnabled && inBounds(event, SETTINGS_CHANGE_X_POSITION, SETTINGS_CHANGE_Y_POSITION, SETTINGS_CHANGE_WIDTH, SETTINGS_CHANGE_HEIGHT)) {
                        Settings.soundEnabled = true;
                        // If Settings window open, check for back button press or SETTINGS button press to
                        // update appropriate variable and save Settings file
                    } else if (at_settings && inBounds(event, SETTINGS_BACK_X_POSITION, SETTINGS_BACK_Y_POSITION, SETTINGS_BACK_WIDTH, SETTINGS_BACK_HEIGHT) || inBounds(event, 95, 680, 290, 76)) {
                        at_settings = false;
                        Settings.save(game.getFileIO());
                    }
                    // If RANKS button pressed, update at_leaderboards to cause Google API to open
                    // leaderboards window
                    // CHANGE WHEN LEADERBOARDS FULLY IMPLEMENTED
                    if (!at_settings && inBounds(event, 95, 572, 250, 76)) {
                        at_leaderboards = true;
                    }

                    if (!at_settings && inBounds(event, 95, 572, 250, 76)) {
                        // TODO: Uncomment below
                        //game.goToLeaderboard();
                        Log.i("MainMenuS", "went to Leaderboards");
                    }


                }
            }
        }

    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.clearScreen(Color.WHITE);


        g.drawImage(this.mainMenuSplash, 0, 0);

        // Draw Settings window if appropriate
        if (at_settings) {
            int SETTINGS_WINDOW_X_POSITION = 50;
            int SETTINGS_WINDOW_Y_POSITION = 388;
            if (Settings.soundEnabled) {
                g.drawImage(this.sound_enabled_img, SETTINGS_WINDOW_X_POSITION, SETTINGS_WINDOW_Y_POSITION);
            } else if (!Settings.soundEnabled) {
                g.drawImage(this.sound_disabled_img, SETTINGS_WINDOW_X_POSITION, SETTINGS_WINDOW_Y_POSITION);
            }
        }
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
        // The user seems to want to quit.
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}