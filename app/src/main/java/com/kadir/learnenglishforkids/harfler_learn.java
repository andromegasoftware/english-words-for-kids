package com.kadir.learnenglishforkids;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Random;

public class harfler_learn extends AppCompatActivity {

    private AdView madview;

    Handler h = new Handler();

    Button ileri;
    Button anasayfa;
    ImageView image;
    Random random = new Random();
    int diziileri = 0;

    Button ses_arti;
    Button ses_eksi;

    MediaPlayer hayvan_sesi;
    ArrayList<Integer> array_ses = new ArrayList<Integer>();
    ArrayList<Integer> array_image = new ArrayList<Integer>();

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harfler_learn);

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

        MobileAds.initialize(this, "ca-app-pub-3206398076180977~1188508498");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3206398076180977/8684812993");

        madview = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);

        //**************inersteller ad***********************
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3206398076180977/2090226859");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //******************************************************

        array_image.add(R.mipmap.a);
        array_image.add(R.mipmap.b);
        array_image.add(R.mipmap.c);
        array_image.add(R.mipmap.d);
        array_image.add(R.mipmap.e);
        array_image.add(R.mipmap.f);
        array_image.add(R.mipmap.g);
        array_image.add(R.mipmap.h);
        array_image.add(R.mipmap.i);
        array_image.add(R.mipmap.j);
        array_image.add(R.mipmap.k);
        array_image.add(R.mipmap.l);
        array_image.add(R.mipmap.m);
        array_image.add(R.mipmap.n);
        array_image.add(R.mipmap.o);
        array_image.add(R.mipmap.p);
        array_image.add(R.mipmap.q);
        array_image.add(R.mipmap.r);
        array_image.add(R.mipmap.s);
        array_image.add(R.mipmap.t);
        array_image.add(R.mipmap.u);
        array_image.add(R.mipmap.v);
        array_image.add(R.mipmap.w);
        array_image.add(R.mipmap.x);
        array_image.add(R.mipmap.w);
        array_image.add(R.mipmap.z);



        array_ses.add(R.raw.a);
        array_ses.add(R.raw.b);
        array_ses.add(R.raw.c);
        array_ses.add(R.raw.d);
        array_ses.add(R.raw.e);
        array_ses.add(R.raw.f);
        array_ses.add(R.raw.g);
        array_ses.add(R.raw.h);
        array_ses.add(R.raw.i);
        array_ses.add(R.raw.j);
        array_ses.add(R.raw.k);
        array_ses.add(R.raw.l);
        array_ses.add(R.raw.m);
        array_ses.add(R.raw.n);
        array_ses.add(R.raw.o);
        array_ses.add(R.raw.p);
        array_ses.add(R.raw.q);
        array_ses.add(R.raw.r);
        array_ses.add(R.raw.s);
        array_ses.add(R.raw.t);
        array_ses.add(R.raw.u);
        array_ses.add(R.raw.v);
        array_ses.add(R.raw.w);
        array_ses.add(R.raw.x);
        array_ses.add(R.raw.y);
        array_ses.add(R.raw.z);




        image = (ImageView) findViewById(R.id.imageView);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hayvan_sesi.start();
            }
        });
        oyun();
        ileri = (Button) findViewById(R.id.ileri);
        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(array_image.size() == 0)
                {
                    image.setVisibility(View.INVISIBLE);
                    ileri.setVisibility(View.INVISIBLE);

                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //*******intersteller ad başlar***********
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                Intent i = new Intent(harfler_learn.this, harfler_learn_sinav.class);
                                startActivity(i);
                                finish();
                                System.out.println("ad yüklenemedi.");
                            }

                            mInterstitialAd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    // Code to be executed when the interstitial ad is closed.
                                    Intent i = new Intent(harfler_learn.this, harfler_learn_sinav.class);
                                    startActivity(i);
                                    finish();
                                }
                            });
                            //***************************
                        }
                    }, 1000);
                }
                else
                {
                    oyun();
                }


            }
        });

        anasayfa = (Button)findViewById(R.id.anasayfa);
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(harfler_learn.this, activity_giris_iki.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void oyun()
    {
        diziileri = 0;
        image.setImageResource(array_image.get(diziileri));
        hayvan_sesi = MediaPlayer.create(this, array_ses.get(diziileri));
        hayvan_sesi.start();
        array_image.remove(diziileri);
        array_ses.remove(diziileri);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(harfler_learn.this, giris.class);
        startActivity(i);
        finish();

    }
}
