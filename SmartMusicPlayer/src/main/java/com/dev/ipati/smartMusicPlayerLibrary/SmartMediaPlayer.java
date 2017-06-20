package com.dev.ipati.smartMusicPlayerLibrary;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class SmartMediaPlayer implements OnSmartListenerMediaPlayer {
    private MediaPlayer mMediaPlayer;
    private Context mContextListener;
    private ArrayList<Integer> mRawList;
    private File fileMusic;
    private ArrayList<File> listFileMusic;
    private Integer mRaw;
    private Uri path;
    private int index;

    //Todo:Parameter when Next and Prev
    private boolean cropCircleImage;
    private ImageView mImageCover;
    private Integer placeHolder;

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

    //Todo:PlayeSigleMusic
    public SmartMediaPlayer(Context mContext, MediaPlayer mp, File fileMusic) {
        this.mContextListener = mContext;
        this.mMediaPlayer = mp;
        this.fileMusic = fileMusic;
        onStandByListener();
    }

    //Todo:MultiPlayerInDevice
    public SmartMediaPlayer(Context mContext, MediaPlayer mMediaPlayer, ArrayList<File> listMusic, boolean usedState) {
        if (usedState) {
            this.mContextListener = mContext;
            this.mMediaPlayer = mMediaPlayer;
            this.listFileMusic = listMusic;
            onStandByListener();
        }
    }


    //Todo:Loading Standby
    private void onStandByListener() {
        if (mRaw != null) {
            path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRaw);
            mMediaPlayer = MediaPlayer.create(mContextListener, path);
        } else if (fileMusic != null) {
            path = Uri.parse(fileMusic.getPath());
            mMediaPlayer = MediaPlayer.create(mContextListener, path);
        } else if (mRawList != null) {
            index = 0;
            path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRawList.get(index));
            mMediaPlayer = MediaPlayer.create(mContextListener, path);
        } else if (listFileMusic != null) {
            index = 0;
            path = Uri.parse(listFileMusic.get(index).getPath());
            mMediaPlayer = MediaPlayer.create(mContextListener, path);
        } else {
            Toast.makeText(mContextListener, "Please Add Music !!!", Toast.LENGTH_SHORT).show();
        }

    }


    //Todo:ImageCoverDiskGlide
    public void setBitmapImageCover(Context mContext, int placeHolder, ImageView imCover, boolean cropCircleImage) {
        if (imCover != null) {
            this.cropCircleImage = cropCircleImage;
            this.mImageCover = imCover;
            this.placeHolder = placeHolder;
            MediaMetadataRetriever mRetriever = new MediaMetadataRetriever();
            mRetriever.setDataSource(mContext, path);
            byte[] imByte = mRetriever.getEmbeddedPicture();

            if (!cropCircleImage) {
                Glide.with(mContext)
                        .load(imByte)
                        .crossFade()
                        .placeholder(placeHolder)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imCover);
            } else {
                setBitmapImageCoverCircle(mContext, placeHolder, imCover, imByte);
            }
        }

    }

    private void setBitmapImageCoverCircle(Context mContext, int placeHolder, ImageView imCover, byte[] byteImage) {
        Glide.with(mContext)
                .load(byteImage)
                .centerCrop()
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
            FunctionNextGenerator(mRawList, null);

        } else if (listFileMusic != null) {
            FunctionNextGenerator(null, listFileMusic);
        }
    }

    private void FunctionNextGenerator(ArrayList<Integer> listMusic, ArrayList<File> listFileMusic) {
        if (listMusic != null) {
            index++;
            if (index < mRawList.size()) {
                Log.d("index", String.valueOf(index) + ":" + String.valueOf(mRawList.size()));
                path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRawList.get(index));
                mMediaPlayer.reset();
                try {
                    mMediaPlayer.setDataSource(mContextListener, path);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    setImageContinueButton(path);
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
                    setImageContinueButton(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (listFileMusic != null) {
            index++;
            if (index < listFileMusic.size()) {
                path = Uri.parse(listFileMusic.get(index).getPath());
                mMediaPlayer.reset();
                try {
                    mMediaPlayer.setDataSource(mContextListener, path);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    setImageContinueButton(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                index = 0;
                path = Uri.parse(listFileMusic.get(index).getPath());
                mMediaPlayer.reset();
                try {
                    mMediaPlayer.setDataSource(mContextListener, path);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    setImageContinueButton(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(mContextListener, "This is SinglePlayer", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnMusicPrevListener() {
        if (mRawList != null) {
            FunctionPrevGenerator(mRawList, null);
        } else if (listFileMusic != null) {
            FunctionPrevGenerator(null, listFileMusic);
        }
    }

    private void FunctionPrevGenerator(ArrayList<Integer> listMusic, ArrayList<File> listFileMusic) {
        if (listMusic != null) {
            index--;
            if (index >= 0 && index < mRawList.size()) {
                path = Uri.parse("android.resource://" + mContextListener.getPackageName() + "/" + mRawList.get(index));
                mMediaPlayer.reset();
                try {
                    mMediaPlayer.setDataSource(mContextListener, path);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    setImageContinueButton(path);
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
                    setImageContinueButton(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (listFileMusic != null) {
            index--;
            if (index >= 0 && index < listFileMusic.size()) {
                path = Uri.parse(listFileMusic.get(index).getPath());
                mMediaPlayer.reset();
                try {
                    mMediaPlayer.setDataSource(mContextListener, path);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    setImageContinueButton(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                index = 0;
                path = Uri.parse(listFileMusic.get(index).getPath());
                mMediaPlayer.reset();
                try {
                    mMediaPlayer.setDataSource(mContextListener, path);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    setImageContinueButton(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(mContextListener, "This is Play First Music", Toast.LENGTH_SHORT).show();
        }
    }

    private void setImageContinueButton(Uri path) {
        if (mImageCover != null) {
            MediaMetadataRetriever mRetriever = new MediaMetadataRetriever();
            mRetriever.setDataSource(mContextListener, path);
            byte[] imByte = mRetriever.getEmbeddedPicture();

            if (!cropCircleImage) {
                Glide.with(mContextListener)
                        .load(imByte)
                        .crossFade()
                        .placeholder(placeHolder)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(mImageCover);
            } else {
                setBitmapImageCoverCircle(mContextListener, placeHolder, mImageCover, imByte);
            }
        }
    }

    public MediaPlayer getmMediaPlayer() {
        return mMediaPlayer;
    }

    public ImageView getmImageCover() {
        return mImageCover;
    }
}
