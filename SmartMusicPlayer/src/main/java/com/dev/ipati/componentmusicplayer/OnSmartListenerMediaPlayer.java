package com.dev.ipati.componentmusicplayer;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by ipati on 6/6/2560.
 */

public interface OnSmartListenerMediaPlayer {
    void OnMusicStartListener();

    void OnMusicPauseListener();

    void OnMusicNextListener();

    void OnMusicPrevListener();
}
