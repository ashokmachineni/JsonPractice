package com.ashok.jsonpractice;

/**
 * Created by ashok on 9/27/16.
 */
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by ashok on 9/21/16.
 */
public class VideoPlayer extends AppCompatActivity {
    public static final String EXTRA_VIDEO_URL = "video_url";
    public static String pathToFileOrUrl ;
    private WebView webView;
    private VideoView mVideoView;
    private ProgressBar load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoactivity);
        Vitamio.initialize(getBaseContext());
        setContentView(R.layout.videoactivity);


        if (getIntent().getExtras() != null) {
            pathToFileOrUrl = getIntent().getExtras().getString(EXTRA_VIDEO_URL);
        }

        if (!LibsChecker.checkVitamioLibs(this))
            return;


        mVideoView = (VideoView) findViewById(R.id.surface_view);



            /*
             * Alternatively,for streaming media you can use
             * mVideoView.setVideoURI(Uri.parse(URLstring));
             */
        mVideoView.setVideoURI(Uri.parse(pathToFileOrUrl));
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                load=(ProgressBar) findViewById(R.id.loading);
                load.setVisibility(View.GONE);
                // optional need Vitamio 4.0
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });

        getSupportActionBar().hide();

    }

    public void startPlay(View view) {
        if (!TextUtils.isEmpty(pathToFileOrUrl)) {
            mVideoView.setVideoPath(pathToFileOrUrl);
        }
    }

    public void openVideo(View View) {
        mVideoView.setVideoPath(pathToFileOrUrl);
    }

}