package com.kadir.learnenglishforkids;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class activity_giris_iki extends AppCompatActivity {

    Button harfler;
    Button mutfak;
    Button banyo;
    Button buzdolabi;
    Button kiyafetler;
    Button ev;
    Button girise_git;
    MediaPlayer media;
    Button ses_arti;
    Button ses_eksi;
    Button hoparlor;

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_iki);

        MobileAds.initialize(this, "ca-app-pub-3206398076180977~1188508498"); //reklamı initialise etmek için
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3206398076180977/2090226859");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        final AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);



        ses_arti = (Button)findViewById(R.id.arti);
        ses_arti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);

            }
        });

        ses_eksi = (Button)findViewById(R.id.eksi);
        ses_eksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
            }
        });

        hoparlor = (Button)findViewById(R.id.buttonhop);
        hoparlor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(media.isPlaying())
                {
                    media.pause();
                    hoparlor.setBackgroundResource(R.mipmap.audiono);

                }
                else {
                    media.start();
                    hoparlor.setBackgroundResource(R.mipmap.audio);
                }

            }
        });

        media = MediaPlayer.create(this, R.raw.music);
        media.start();

        harfler = (Button)findViewById(R.id.harfler);
        harfler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(activity_giris_iki.this, harfler_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(activity_giris_iki.this, harfler_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        mutfak = (Button)findViewById(R.id.mutfak);
        mutfak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(activity_giris_iki.this, mutfak_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(activity_giris_iki.this, mutfak_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

       banyo = (Button)findViewById(R.id.banyo);
        banyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(activity_giris_iki.this, banyo_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(activity_giris_iki.this, banyo_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        buzdolabi = (Button)findViewById(R.id.buzdolabi);
        buzdolabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(activity_giris_iki.this, buzdolabi_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(activity_giris_iki.this, buzdolabi_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        kiyafetler = (Button)findViewById(R.id.kiyafetler);
        kiyafetler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(activity_giris_iki.this, kiyafetler_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(activity_giris_iki.this, kiyafetler_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        ev = (Button)findViewById(R.id.ev);
        ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(activity_giris_iki.this, ev_esyalari_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(activity_giris_iki.this, ev_esyalari_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        girise_git = (Button)findViewById(R.id.girise_git);
        girise_git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(activity_giris_iki.this, giris.class);
                startActivity(i);
                media.stop();
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(activity_giris_iki.this, giris.class);
        startActivity(i);
        media.stop();
        finish();
    }

    @Override
    protected void onPause() {
        media.pause();
        super.onPause();
    }
}
