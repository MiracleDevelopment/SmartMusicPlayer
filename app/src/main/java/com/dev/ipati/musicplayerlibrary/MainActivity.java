package com.dev.ipati.musicplayerlibrary;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dev.ipati.componentmusicplayer.OnSmartListenerMediaPlayer;
import com.dev.ipati.componentmusicplayer.SmartMediaPlayer;

public class MainActivity extends AppCompatActivity {
    SmartMediaPlayer smartMediaPlayer;
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smartMediaPlayer = new SmartMediaPlayer(getApplicationContext(), mMediaPlayer, R.raw.magic, null);
        smartMediaPlayer.OnMusicStartListener(true);
    }
}
