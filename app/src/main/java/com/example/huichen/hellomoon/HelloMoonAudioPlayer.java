package com.example.huichen.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by huichen on 8/13/14.
 */
public class HelloMoonAudioPlayer {
    private MediaPlayer             mPlayer;
    private OnCompletionListener    mOnCompletionListener;

    public boolean isPlaying() {
        if (mPlayer != null)
        {
            return mPlayer.isPlaying();
        }

        return false;
    }

    public void play(Context context) {
        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(context, R.raw.one_small_step);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stop();
                    mOnCompletionListener.onCompletion(HelloMoonAudioPlayer.this);
                }
            });
        }
        mPlayer.start();
    }

    public void pause() {
        if (mPlayer != null) {
            mPlayer.pause();
        }
    }

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void setOnCompletionListener(HelloMoonAudioPlayer.OnCompletionListener listener) {
        mOnCompletionListener = listener;
    }

    public static interface OnCompletionListener {
        void onCompletion(HelloMoonAudioPlayer audioPlayer);
    }
}
