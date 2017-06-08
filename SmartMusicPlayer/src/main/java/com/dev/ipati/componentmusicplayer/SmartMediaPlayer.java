package com.dev.ipati.componentmusicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ipati on 6/6/2560.
 */

public class SmartMediaPlayer implements OnSmartListenerMediaPlayer {
    private MediaPlayer mMediaPlayer;
    private Context mContextListener;
    private ArrayList<Integer> mRawList;
    private Integer mRaw;
    private Uri path;
    private int index;

    public SmartMediaPlayer(Context mContext, MediaPlayer mp, Integer startAtMusic) {
        this.mContextListener = mContext;
        this.mMediaPlayer = mp;
        this.mRaw = startAtMusic;
        onStandByListener();
    }

    public SmartMediaPlayer(Context mContext, MediaPlayer mp, ArrayList<Integer> Raw) {
        this.mContextListener = mContext;
        this.mMediaPlayer = mp;
        this.mRawList = Raw;
        onStandByListener();
    }

    private void onStandByListener() {
        if (mRaw != null) {
            path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRaw);
            mMediaPlayer = MediaPlayer.create(mContextListener, path);
        } else {
            index = 0;
            path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRawList.get(index));
            mMediaPlayer = MediaPlayer.create(mContextListener, path);
        }

    }

    @Override
    public void OnMusicStartListener() {
        if (mMediaPlayer != null) {
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.start();
            }
        }
    }

    @Override
    public void OnMusicPauseListener() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }


    @Override
    public void OnMusicNextListener() {
        if (mRawList != null) {
            index++;
            if (index < mRawList.size()) {
                Log.d("index", String.valueOf(index) + ":" + String.valueOf(mRawList.size()));
                path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRawList.get(index));
                mMediaPlayer.reset();
                try {
                    mMediaPlayer.setDataSource(mContextListener, path);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                index = 0;
                path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRawList.get(index));
                mMediaPlayer.reset();
                try {
                    mMediaPlayer.setDataSource(mContextListener, path);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void OnMusicPrevListener() {
        if (mRawList != null) {
            index--;
            if (index >= 0 && index < mRawList.size()) {
                path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRawList.get(index));
                mMediaPlayer.reset();
                try {
                    mMediaPlayer.setDataSource(mContextListener, path);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                index = 0;
                path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRawList.get(index));
                mMediaPlayer.reset();
                try {
                    mMediaPlayer.setDataSource(mContextListener, path);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
