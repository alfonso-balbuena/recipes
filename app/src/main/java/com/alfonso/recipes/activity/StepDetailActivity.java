package com.alfonso.recipes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.alfonso.recipes.R;
import com.alfonso.recipes.utils.MediaUtils;
import com.alfonso.recipes.viewModel.StepDetailViewModel;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class StepDetailActivity extends AppCompatActivity {
    private SimpleExoPlayer mExoPlayer;
    private StepDetailViewModel viewModel;
    private MediaSessionCompat mMediaSession = null;
    private PlaybackStateCompat.Builder mStateBuilder = null;
    private final String TAG = StepDetailActivity.class.getSimpleName();
    private final String POSITION_KEY = "position";
    private Long position = 0L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        Intent intent = getIntent();
        viewModel = new ViewModelProvider(this).get(StepDetailViewModel.class);

        if (intent.hasExtra(RecipeDetailActivity.LIST_STEP) && savedInstanceState == null) {
            viewModel.setCurrentStep(intent.getParcelableExtra(RecipeDetailActivity.STEP_SELECTED));
            viewModel.setListSteps(intent.getParcelableArrayListExtra(RecipeDetailActivity.LIST_STEP));
        }

        if(intent.hasExtra(RecipeDetailActivity.RECIPE_NAME)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(intent.getStringExtra(RecipeDetailActivity.RECIPE_NAME));
        }
        loadCurrentData();
        if(savedInstanceState != null) {
            position = viewModel.getPositionVideo();
        }
        Button btnNext = findViewById(R.id.btn_next_step);
        btnNext.setOnClickListener(view -> {
            next();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initSession();
        initExoPlayer();
        changeVideo();
        mExoPlayer.seekTo(position);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initExoPlayer() {
        StyledPlayerView playerView = findViewById(R.id.player_video_step);
        if(mExoPlayer == null) {
            mExoPlayer = MediaUtils.initExoPlayer(this,mStateBuilder,mMediaSession);
            playerView.setPlayer(mExoPlayer);
        }
    }

    private void initSession() {
        mMediaSession = MediaUtils.initSession(this,TAG);
        mStateBuilder = MediaUtils.initPlayBackStateCompatBuildeR(mMediaSession);
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
    }

    private void loadCurrentData() {
        TextView tvDescription = findViewById(R.id.tv_all_description_step);
        tvDescription.setText(viewModel.getCurrentStep().getDescription());
        changeVideo();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mExoPlayer.pause();
    }

    private void releasePlayer(){
        viewModel.setPositionVideo(mExoPlayer.getContentPosition());
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMediaSession.setActive(false);
        releasePlayer();
    }

    private void changeVideo() {
        if(mExoPlayer != null) {
            String url = viewModel.getCurrentStep().getVideoURLToExoPlayer();
            mExoPlayer.setMediaItem(MediaItem.fromUri(url),0);
            mExoPlayer.prepare();
            mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,mExoPlayer.getContentPosition(),1f);
        }
    }

    private void next() {
        viewModel.next();
        loadCurrentData();
        if (viewModel.isLast()) {
            Button buttonNext = findViewById(R.id.btn_next_step);
            buttonNext.setEnabled(false);
        }
    }
}