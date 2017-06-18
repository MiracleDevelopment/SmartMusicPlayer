package com.dev.ipati.smartMusicPlayerLibrary;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.dev.ipati.componentmusicplayer.R;

import java.io.IOException;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class SmartMediaPlayer implements OnSmartListenerMediaPlayer {
    private MediaPlayer mMediaPlayer;
    private Context mContextListener;
    private ArrayList<Integer> mRawList;
    private Integer mRaw;
    private Uri path;
    private int index;

    //Todo:Single Player
    public SmartMediaPlayer(Context mContext, MediaPlayer mp, Integer startAtMusic) {
        this.mContextListener = mContext;
        this.mMediaPlayer = mp;
        this.mRaw = startAtMusic;
        onStandByListener();
    }

    //Todo:MultiPlayer
    public SmartMediaPlayer(Context mContext, MediaPlayer mp, ArrayList<Integer> Raw) {
        this.mContextListener = mContext;
        this.mMediaPlayer = mp;
        this.mRawList = Raw;
        onStandByListener();
    }

    //Todo:Loading Standby
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

    //Todo:ImageCoverDiskGlide
    public void setBitmapImageCover(Context mContext, int placeHolder, ImageView imCover, boolean cropCircleImage) {
        MediaMetadataRetriever mRetriever = new MediaMetadataRetriever();
        mRetriever.setDataSource(mContext, path);
        byte[] imByte = mRetriever.getEmbeddedPicture();
        if (imCover.getDrawable() == null) {
            if (!cropCircleImage) {
                Glide.with(mContext)
                        .load(imByte)
                        .placeholder(placeHolder)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imCover);
            } else {
                setBitmapImageCoverCircle(mContext, placeHolder, imCover, imByte);
            }
        }
    }

    private void setBitmapImageCoverCircle(Context mContext, int placeHolder, ImageView imCover, byte[] byteImage) {
        Glide.with(mContext).load(byteImage).centerCrop()
                .crossFade()
                .bitmapTransform(new CropCircleTransformation(mContext))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeHolder)
                .into(imCover);
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
