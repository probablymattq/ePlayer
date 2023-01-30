package com.example.firstandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button click = (Button) findViewById(R.id.button);
        click.setBackgroundColor(Color.parseColor("#000000"));
        click.setTextColor(Color.parseColor("#FFFFFF"));
        Button click2 = (Button) findViewById(R.id.button3);
        click2.setBackgroundColor(Color.parseColor("#000000"));
        click2.setTextColor(Color.parseColor("#FFFFFF"));
        click2.setTextAppearance(R.style.ButtonTextAppearance);

        Button click3 = (Button) findViewById(R.id.button4);
        click3.setBackgroundColor(Color.parseColor("#000000"));
        click3.setTextColor(Color.parseColor("#FFFFFF"));
        Button click4 = (Button) findViewById(R.id.button2);
        click4.setBackgroundColor(Color.parseColor("#000000"));
        click4.setTextColor(Color.parseColor("#FFFFFF"));
        Button click5 = (Button) findViewById(R.id.button5);
        click5.setBackgroundColor(Color.parseColor("#000000"));
        click5.setTextColor(Color.parseColor("#FFFFFF"));

        final int[] currentTrack = {new Random().nextInt(10)};

        final boolean[] isPlaying = {false};
        int[] tracks = new int[]{R.raw.track1, R.raw.track2, R.raw.track3, R.raw.track4, R.raw.track5, R.raw.track6, R.raw.track7, R.raw.track8, R.raw.track9, R.raw.track10};
        int[] thumbnails = new int[]{R.drawable.track1, R.drawable.track2, R.drawable.track3, R.drawable.track4, R.drawable.track5, R.drawable.track6, R.drawable.track7, R.drawable.track8, R.drawable.track9, R.drawable.track10};
        String[] trackNames = new String[]{"Ilybrolly & Slxrppy - Tek it! (prod. Tydavid)", "Ze66y - Jane Breaking Bad (Prod. Sh4k x Dentist)", "DeeKay - Lovestruck! (prod. splxtstxcs)", "YukiBeats ft. Kuma The Third - SASS", "Znip - Lemon Toadies (prod. ForsakenHydra)",
        "Lil Agony x Whispersinyahead - Fallen (prod. AGONYSGRAVE)", "INFAMXVS - PHONK AERIALS (feat. LUCCI DAMUS)", "k!dd - Spvzzin P2 (feat. Moee Da Vinci)", "Crewsont x Ace Shadows - Hannibal", "Tha Freek - They Must Die (Kill All Rcists)"};


        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying[0]) {
                    mediaPlayer.start();
                } else {
                    if (!mediaPlayer.isPlaying()) {
                        try {
                            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + tracks[currentTrack[0]]));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        mediaPlayer.start();
                        isPlaying[0] = true;
                    }
                }
            }
        });


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        });


        click3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    isPlaying[0] = false;
                    currentTrack[0] = new Random().nextInt(10);
                }
            }
        });

        click4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTrack[0] = (currentTrack[0] + 1) % tracks.length;
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + tracks[currentTrack[0]]));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mediaPlayer.start();
            }
        });

        click5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTrack[0] = (currentTrack[0] + tracks.length - 1) % tracks.length;
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + tracks[currentTrack[0]]));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mediaPlayer.start();
            }
        });


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void playNextTrack() {
                currentTrack[0] = (currentTrack[0] + 1) % tracks.length;
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + tracks[currentTrack[0]]));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mediaPlayer.start();
            }

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playNextTrack();
            }
        });

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final TextView textView = findViewById(R.id.textView);
        final TextView textView2 = findViewById(R.id.textView2);
        final TextView textView3 = findViewById(R.id.textView3);
        final ImageView imageView = findViewById(R.id.imageView2);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    if (isPlaying[0]) {
                        mediaPlayer.start();
                    } else {
                        if (!mediaPlayer.isPlaying()) {
                            try {
                                mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + tracks[currentTrack[0]]));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                mediaPlayer.prepare();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            mediaPlayer.start();
                            isPlaying[0] = true;
                        }
                    }
                }
            }
        });

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mediaPlayer != null) {
                    textView2.setText(trackNames[currentTrack[0]]);
                    imageView.setImageResource(thumbnails[currentTrack[0]]);

                    int length = mediaPlayer.getDuration();
                    int minutes = length / 1000 / 60;
                    int seconds = length / 1000 % 60;
                    @SuppressLint("DefaultLocale") String time = String.format("%02d:%02d", minutes, seconds);
                    textView.setText(time);

                    progressBar.setMax(mediaPlayer.getDuration());
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#000000"));
                    progressBar.setProgressTintList(colorStateList);

                    new Timer().scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if (mediaPlayer.isPlaying()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        int length = mediaPlayer.getCurrentPosition();
                                        int minutes = length / 1000 / 60;
                                        int seconds = length / 1000 % 60;
                                        @SuppressLint("DefaultLocale") String time = String.format("%02d:%02d", minutes, seconds);
                                        textView3.setText(time);
                                    }
                                });

                                progressBar.setProgress(mediaPlayer.getCurrentPosition());
                            }
                        }
                    }, 0, 1000);
                }
            }
        });
    }
}