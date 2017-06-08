package com.dev.ipati.musicplayerlibrary;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dev.ipati.componentmusicplayer.OnSmartListenerMediaPlayer;
import com.dev.ipati.componentmusicplayer.SmartMediaPlayer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SmartMediaPlayer smartMediaPlayer;
    MediaPlayer mMediaPlayer;
    Button mPlay, mPause, mNext, mPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlay = (Button) findViewById(R.id.bt_play);
        mPause = (Button) findViewById(R.id.bt_pause);
        mNext = (Button) findViewById(R.id.bt_next);
        mPrev = (Button) findViewById(R.id.bt_prev);


        smartMediaPlayer = new SmartMediaPlayer(getApplicationContext(), mMediaPlayer, R.raw.magic);
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smartMediaPlayer.OnMusicStartListener();
            }
        });

        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smartMediaPlayer.OnMusicPauseListener();
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smartMediaPlayer.OnMusicNextListener();
            }
        });

        mPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smartMediaPlayer.OnMusicPrevListener();
            }
        });
    }
}
