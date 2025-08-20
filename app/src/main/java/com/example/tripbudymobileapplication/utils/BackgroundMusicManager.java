package com.example.tripbudymobileapplication.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.tripbudymobileapplication.R;

import java.util.Random;

public class BackgroundMusicManager {
    private static MediaPlayer mediaPlayer;

    public static void play(Context context){
        mediaPlayer = MediaPlayer.create(context, R.raw.background);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public static void pause(){
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public static void resume(){
        if (mediaPlayer != null && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    public static void stop(){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
