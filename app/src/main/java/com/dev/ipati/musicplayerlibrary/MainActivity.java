package com.dev.ipati.musicplayerlibrary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.dev.ipati.smartMusicPlayerLibrary.SmartMediaPlayer;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    SmartMediaPlayer smartMediaPlayer;
    MediaPlayer mMediaPlayer;
    Button mPlay, mPause, mNext, mPrev;
    ImageView imAlbum;
    ArrayList<String> listFilePlayer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlay = (Button) findViewById(R.id.bt_play);
        mPause = (Button) findViewById(R.id.bt_pause);
        mNext = (Button) findViewById(R.id.bt_next);
        mPrev = (Button) findViewById(R.id.bt_prev);
        imAlbum = (ImageView) findViewById(R.id.im_album);

        smartMediaPlayer = new SmartMediaPlayer(getApplicationContext(), mMediaPlayer, R.raw.labanoon);
        smartMediaPlayer.setBitmapImageCover(getApplicationContext()
                , R.mipmap.ic_launcher, imAlbum
                , true);

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

    private void SaveData() {
        int indexContent;
        Cursor mCursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Audio.Media.TITLE + " ASC");

        if (mCursor != null) {
            indexContent = mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
            while (mCursor.moveToNext()) {
                listFilePlayer.add(mCursor.getString(indexContent));
                String path = mCursor.getString(indexContent);
                Toast.makeText(getApplicationContext(), path, Toast.LENGTH_SHORT).show();
            }
            mCursor.close();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1010:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SaveData();
                } else {
                    SaveData();
                }
                break;
        }
    }
}
