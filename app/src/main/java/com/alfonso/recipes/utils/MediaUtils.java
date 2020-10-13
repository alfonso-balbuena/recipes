package com.alfonso.recipes.utils;

import android.content.Context;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;

public class MediaUtils {

    public static SimpleExoPlayer initExoPlayer(Context context,PlaybackStateCompat.Builder mStateBuilder,MediaSessionCompat mMediaSession) {
        SimpleExoPlayer mExoPlayer = new SimpleExoPlayer.Builder(context).build();
        mExoPlayer.addListener(new Player.EventListener() {
                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    if(playbackState == ExoPlayer.STATE_READY && playWhenReady) {
                        mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                                mExoPlayer.getContentPosition(),1f);
                    } else {
                        if(playbackState == ExoPlayer.STATE_READY) {
                            mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
                                    mExoPlayer.getContentPosition(),1f);
                        }
                    }
                    mMediaSession.setPlaybackState(mStateBuilder.build());
                }
            });
        mExoPlayer.setPlayWhenReady(true);
        return mExoPlayer;
    }

    public static MediaSessionCompat initSession(Context context, String TAG) {
        MediaSessionCompat mMediaSession = new MediaSessionCompat(context,TAG);
        mMediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mMediaSession.setMediaButtonReceiver(null);
        return mMediaSession;
    }

    public static PlaybackStateCompat.Builder initPlayBackStateCompatBuildeR(MediaSessionCompat mMediaSession) {
        PlaybackStateCompat.Builder mStateBuilder = new PlaybackStateCompat.Builder().
                setActions(PlaybackStateCompat.ACTION_PLAY |
                        PlaybackStateCompat.ACTION_PAUSE |
                        PlaybackStateCompat.ACTION_PLAY_PAUSE |
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS);
        mMediaSession.setPlaybackState(mStateBuilder.build());
        return mStateBuilder;
    }

}
