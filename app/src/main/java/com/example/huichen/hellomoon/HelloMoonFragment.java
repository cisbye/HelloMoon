package com.example.huichen.hellomoon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by huichen on 8/13/14.
 */
public class HelloMoonFragment extends Fragment {
    private HelloMoonAudioPlayer mPlayer = new HelloMoonAudioPlayer();

    private Button mPlayButton;
    private Button mStopButton;

    private boolean isNeedContinuePlay = false;

    private void pausePlay() {
        mPlayer.pause();
        mPlayButton.setText(R.string.hellomoon_play);
    }

    private void resumePlay() {
        mPlayer.play(getActivity());
        mPlayButton.setText(R.string.hellomoon_pause);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hello_moon, container, false);

        mPlayButton = (Button)v.findViewById(R.id.hellomoon_playButton);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPlayer.isPlaying()) {
                    pausePlay();
                    isNeedContinuePlay = false;
                } else {
                    resumePlay();
                    isNeedContinuePlay = true;
                }
            }
        });

        mStopButton = (Button)v.findViewById(R.id.hellomoon_stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                isNeedContinuePlay = false;
                mPlayButton.setText(R.string.hellomoon_play);
            }
        });

        mPlayer.setOnCompletionListener(new HelloMoonAudioPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(HelloMoonAudioPlayer audioPlayer) {
                mPlayButton.setText(R.string.hellomoon_play);
            }
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        pausePlay();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isNeedContinuePlay) {
            resumePlay();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }
}
