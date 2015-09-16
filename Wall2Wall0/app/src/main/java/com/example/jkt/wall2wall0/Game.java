// NOT BEING USED, THE GAME CLASS HAS BEEN MOVED TO MAIN ACTIVITY

package com.example.jkt.wall2wall0;

import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

public interface Game {

    public Audio getAudio();

    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen();

    public GoogleApiClient getPlayServicesClient();

    public Context getAppContext();

    void goToLeaderboard();
}
