package com.dev.ipati.smartMusicPlayerLibrary;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ipati on 20/6/2560.
 */

public class ListOnSmartMediaPlayer extends SmartMediaPlayer {
    public ListOnSmartMediaPlayer(Context mContext, MediaPlayer mp, Integer startAtMusic) {
        super(mContext, mp, startAtMusic);
    }

    public ListOnSmartMediaPlayer(Context mContext, MediaPlayer mp, ArrayList<Integer> Raw) {
        super(mContext, mp, Raw);
    }

    public ListOnSmartMediaPlayer(Context mContext, MediaPlayer mp, File fileMusic) {
        super(mContext, mp, fileMusic);
    }

    public ListOnSmartMediaPlayer(Context mContext, MediaPlayer mp, ArrayList<File> listFileMusic, boolean stateUse) {
        super(mContext, mp, listFileMusic, stateUse);
    }
}
