package com.example.jkt.wall2wall0;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.jkt.wall2wall0.impl.AndroidGame;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.api.zze;
import com.google.android.gms.games.Games;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * Created by JDK on 3/30/2015.
 */

// W2WGame class corresponds to the usual MainActivity, and is the first class run upon application
// start
public class W2WGame extends AndroidGame {

    public static String map;
    boolean firstTimeCreate = true;

    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";
    // Bool to track whether the app is already resolving an error
    private static boolean mResolvingError = false;


    @Override
    public Screen getInitScreen() {
        if (firstTimeCreate) {
            Settings.load(getFileIO());
            firstTimeCreate = false;
        }
        Log.i("W2WGame", "getInitScreen");

        return new MainMenuScreen(this);
    }

    @Override
    public void onBackPressed() {
        //getCurrentScreen().backButton();
    }

    public void goToLeaderboard() {
        startActivityForResult(Games.Leaderboards.getLeaderboardIntent(this.play_services_client,
                getString(R.string.leaderboard_high_score)), 1); // Last arg is an arbitrary int
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    //@Override
    public Screen getStartScreen() {
        return null;
    }

    //@Override
    public int getScreenWidth() {
        return 0;
    }

    //@Override
    public int getScreenHeight() {
        return 0;
    }

    //@Override
    public int getScreenOrientation() {
        return 0;
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (mResolvingError) {
            // Already attempting to resolve an error.
            return;
        } else if (connectionResult.hasResolution()) {
            try {
                mResolvingError = true;
                connectionResult.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                play_services_client.connect();
            }
        } else {
            // Show dialog using GooglePlayServicesUtil.getErrorDialog()
            showErrorDialog(connectionResult.getErrorCode());
            mResolvingError = true;
        }
    }



    // The rest of this code is all about building the error dialog

     //Creates a dialog for an error message
    private void showErrorDialog(int errorCode) {
        // Create a fragment for the error dialog
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(this.getFragmentManager(), "errordialog");
    }

     //Called from ErrorDialogFragment when the dialog is dismissed.
    public static void onDialogDismissed() {
        mResolvingError = false;
    }

     //A fragment to display an error dialog
    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment() { }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GooglePlayServicesUtil.getErrorDialog(errorCode,
                    this.getActivity(), REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            onDialogDismissed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!play_services_client.isConnecting() &&
                        !play_services_client.isConnected()) {
                    play_services_client.connect();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
    }
}

/*    public int getHighScore() {
        return this.highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }*/
