package com.example.jkt.wall2wall0.impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.jkt.wall2wall0.Audio;
import com.example.jkt.wall2wall0.FileIO;
import com.example.jkt.wall2wall0.Game;
import com.example.jkt.wall2wall0.Graphics;
import com.example.jkt.wall2wall0.Input;
import com.example.jkt.wall2wall0.Music;
import com.example.jkt.wall2wall0.Screen;
import com.example.jkt.wall2wall0.Sound;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

public abstract class AndroidGame extends Activity implements Game, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    Music music;
    Sound sound;
    public GoogleApiClient play_services_client;
    private static boolean mResolvingError = false;
    protected static final String STATE_RESOLVING_ERROR = "resolving_error";

    WakeLock wakeLock;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        int frameBufferWidth = isPortrait ? 480 : 800;
        int frameBufferHeight = isPortrait ? 800 : 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float scaleX = (float) frameBufferWidth / metrics.widthPixels;
        float scaleY = (float) frameBufferHeight / metrics.heightPixels;

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        screen = getInitScreen();
        setContentView(renderView);

        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
                "MyGame");
    }


    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        //screen.resume();
        renderView.resume();
        Log.i("AndroidGame", "Screen should not be resumed");
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();
        Log.i("AndroidGame", "Screen paused");

        if (isFinishing())
            screen.dispose();
    }

    // Added Synchronized to getInput method
    @Override
    public synchronized Input getInput() {
        return input;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public void setScreen(Screen screen) {
        Log.i("AndroidGame", "setScreen1");
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        Log.i("AndroidGame", "setScreen2");
        //this.screen.pause();
        //this.screen.dispose();
        //screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    public Screen getCurrentScreen() {
        //this.screen.update(0);
        return this.screen;
    }

    @Override
    public void onStop() {
        super.onStop();
        // TODO: Uncomment Below
        //play_services_client.disconnect();
    }

    @Override
    public void onStart() {
    super.onStart();
        Log.i("AndrGame", "starting client construction");

    GoogleApiClient play_services_client = new GoogleApiClient.Builder(this.getApplicationContext())
            .addApi(Plus.API)
            .addScope(Plus.SCOPE_PLUS_LOGIN)
            .setAccountName("users.account.name@gmail.com")
            .build();
        Log.i("AndrGame", "client constructed, starting connection");
    play_services_client.connect();
    }

    public GoogleApiClient getPlayServicesClient() {
        return this.play_services_client;
    }

    public Context getAppContext() {
        return getApplicationContext();
    }
}
