package com.dev.ipati.componentmusicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
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

    public SmartMediaPlayer(Context mContext, MediaPlayer mp, Integer startAtMusic, ArrayList<Integer> Raw) {
        this.mContextListener = mContext;
        this.mMediaPlayer = mp;
        this.mRaw = startAtMusic;
        this.mRawList = Raw;
    }


    @Override
    public void OnMusicStartListener(boolean status) {
        if (mRawList == null) {
            if (status) {
                path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRaw);
                mMediaPlayer = MediaPlayer.create(mContextListener, path);
                if (mMediaPlayer != null) {
                    mMediaPlayer.start();
                } else {
                    Toast.makeText(mContextListener, "Please Insert Music", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mMediaPlayer != null) {
                    mMediaPlayer.pause();
                }
            }
        } else {
            if (status) {
                path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRawList.get(0));
                mMediaPlayer = MediaPlayer.create(mContextListener, path);
                if (mMediaPlayer != null) {
                    mMediaPlayer.start();
                } else {
                    Toast.makeText(mContextListener, "Please Insert Music", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mMediaPlayer != null) {
                    mMediaPlayer.pause();
                }
            }
        }
    }

    @Override
    public void OnMusicNextListener(Context mContext, MediaPlayer mp) {
        if (mMediaPlayer != null) {
            path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRaw);
            mMediaPlayer.stop();
            try {
                mMediaPlayer.setDataSource(mContextListener, path);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void OnMusicPrevListener(Context mContext, MediaPlayer mp) {

    }
}
