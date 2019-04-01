package com.kadir.learnenglishforkids;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class animals_learn extends AppCompatActivity {

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

    ArrayList<String> array_yazi = new ArrayList<String>();  // isimlerin yazıldığı arraylist i tanımlama
    TextView hayvan_isimleri;                               // Textview i tanımlama.

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_learn);

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
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("ca-app-pub-3206398076180977/8684812993").build();
        madview.loadAd(adRequest);

        //**************inersteller ad***********************
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3206398076180977/2090226859");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //******************************************************

        array_image.add(R.mipmap.kaplan);
        array_image.add(R.mipmap.cat);
        array_image.add(R.mipmap.dog);
        array_image.add(R.mipmap.aslan);
        array_image.add(R.mipmap.ordek);
        array_image.add(R.mipmap.kus);
        array_image.add(R.mipmap.tavuk);
        array_image.add(R.mipmap.tilki);
        array_image.add(R.mipmap.zurafa);
        array_image.add(R.mipmap.maymun);


        array_ses.add(R.raw.kaplan);
        array_ses.add(R.raw.kedi);
        array_ses.add(R.raw.kopek);
        array_ses.add(R.raw.aslan);
        array_ses.add(R.raw.ordek);
        array_ses.add(R.raw.kus);
        array_ses.add(R.raw.tavuk);
        array_ses.add(R.raw.tilki);
        array_ses.add(R.raw.zurafa);
        array_ses.add(R.raw.maymun);

        //*******************************************************************
        array_yazi.add(new String("TIGER")); // isimleri diziye ekleme
        array_yazi.add(new String("CAT"));
        array_yazi.add(new String("DOG"));
        array_yazi.add(new String("LION"));
        array_yazi.add(new String("DUCK"));
        array_yazi.add(new String("BIRD"));
        array_yazi.add(new String("CHICKEN"));
        array_yazi.add(new String("FOX"));
        array_yazi.add(new String("GIRAFFE"));
        array_yazi.add(new String("MONKEY"));

        hayvan_isimleri = (TextView)findViewById(R.id.isimler); //isimlerin yazıldığı text i tanımlama.
        //***********************************************************************************************



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
                                    Intent i = new Intent(animals_learn.this, animals_learn_sinav.class);
                                    startActivity(i);
                                    finish();
                                    System.out.println("ad yüklenemedi.");
                                }

                                mInterstitialAd.setAdListener(new AdListener() {
                                    @Override
                                    public void onAdClosed() {
                                        // Code to be executed when the interstitial ad is closed.
                                        Intent i = new Intent(animals_learn.this, animals_learn_sinav.class);
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
                Intent i = new Intent(animals_learn.this, giris.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void oyun()
    {
        diziileri = random.nextInt(array_image.size());
        image.setImageResource(array_image.get(diziileri));

        hayvan_isimleri.setText(array_yazi.get(diziileri)); //isimleri yazdırmak için

        hayvan_sesi = MediaPlayer.create(this, array_ses.get(diziileri));
        hayvan_sesi.start();
        array_image.remove(diziileri);
        array_ses.remove(diziileri);

        array_yazi.remove(diziileri); // isimleri diziden silmek için
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(animals_learn.this, giris.class);
        startActivity(i);
        finish();
    }
}
