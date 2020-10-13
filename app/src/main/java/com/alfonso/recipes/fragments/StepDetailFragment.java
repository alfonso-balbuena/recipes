package com.alfonso.recipes.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alfonso.recipes.R;
import com.alfonso.recipes.models.Step;
import com.alfonso.recipes.utils.MediaUtils;
import com.alfonso.recipes.viewModel.RecipeDetailViewModel;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class StepDetailFragment extends Fragment {

    private RecipeDetailViewModel viewModel;
    private SimpleExoPlayer mExoPlayer;
    private MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;
    private final String TAG = StepDetailFragment.class.getSimpleName();


    public StepDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);
        initMedia(view);
        viewModel = new ViewModelProvider(requireActivity()).get(RecipeDetailViewModel.class);
        viewModel.getSelected().observe(getViewLifecycleOwner(), step -> loadData(step,view));
        return view;
    }

    private void initMedia(View view) {
        mMediaSession = MediaUtils.initSession(getContext(),TAG);
        mStateBuilder = MediaUtils.initPlayBackStateCompatBuildeR(mMediaSession);
        mExoPlayer = MediaUtils.initExoPlayer(getContext(),mStateBuilder,mMediaSession);
        mMediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                mExoPlayer.play();
            }

            @Override
            public void onPause() {
                mExoPlayer.pause();
            }
        });
        StyledPlayerView playerView = view.findViewById(R.id.player_video_step);
        if(mExoPlayer != null) {
            playerView.setPlayer(mExoPlayer);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mExoPlayer.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
        mMediaSession.setActive(false);
    }

    private void releasePlayer(){
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    private void loadData(Step step, View view) {
        TextView textView = view.findViewById(R.id.tv_all_description_step);
        textView.setText(step.getDescription());
        String url = step.getVideoURLToExoPlayer();
        mExoPlayer.setMediaItem(MediaItem.fromUri(url),0);
        mExoPlayer.prepare();
        mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,mExoPlayer.getContentPosition(),1f);
    }
}