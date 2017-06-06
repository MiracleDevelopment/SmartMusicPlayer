package com.dev.ipati.componentmusicplayer;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by ipati on 6/6/2560.
 */

public interface OnSmartListenerMediaPlayer {
    void OnMusicStartListener(boolean status);

    void OnMusicNextListener(Context mContext, MediaPlayer mp);

    void OnMusicPrevListener(Context mContext, MediaPlayer mp);
}
