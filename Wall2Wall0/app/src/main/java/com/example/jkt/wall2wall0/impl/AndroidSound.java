package com.example.jkt.wall2wall0.impl;

import android.media.SoundPool;

import com.example.jkt.wall2wall0.Sound;

public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;
    
    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }


    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    
    public void dispose() {
        soundPool.unload(soundId);
    }

}
