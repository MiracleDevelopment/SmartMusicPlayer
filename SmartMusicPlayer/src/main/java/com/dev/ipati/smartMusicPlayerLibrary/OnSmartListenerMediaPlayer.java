package com.dev.ipati.smartMusicPlayerLibrary;

import android.content.Context;
import android.media.MediaPlayer;



interface OnSmartListenerMediaPlayer {
    void OnMusicStartListener();

    void OnMusicPauseListener();

    void OnMusicNextListener();

    void OnMusicPrevListener();
}
