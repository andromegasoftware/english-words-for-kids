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
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Random;

public class sebzeler_learn extends AppCompatActivity {
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
        setContentView(R.layout.activity_sebzeler_learn);

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

        array_image.add(R.mipmap.domates);
        array_image.add(R.mipmap.patates);
        array_image.add(R.mipmap.sogan);
        array_image.add(R.mipmap.sarimsak);
        array_image.add(R.mipmap.misir);
        array_image.add(R.mipmap.salatalik);
        array_image.add(R.mipmap.bezelye);
        array_image.add(R.mipmap.brokoli);
        array_image.add(R.mipmap.havuc);
        array_image.add(R.mipmap.kabak);


        array_ses.add(R.raw.domates);
        array_ses.add(R.raw.patates);
        array_ses.add(R.raw.sogan);
        array_ses.add(R.raw.sarimsak);
        array_ses.add(R.raw.misir);
        array_ses.add(R.raw.salatalik);
        array_ses.add(R.raw.bezelye);
        array_ses.add(R.raw.brokoli);
        array_ses.add(R.raw.havuc);
        array_ses.add(R.raw.kabak);

        array_yazi.add(new String("TOMATO")); // isimleri diziye ekleme
        array_yazi.add(new String("POTATO"));
        array_yazi.add(new String("ONION"));
        array_yazi.add(new String("GARLIC"));
        array_yazi.add(new String("CORN"));
        array_yazi.add(new String("CUCUMBER"));
        array_yazi.add(new String("PEA"));
        array_yazi.add(new String("BROCCOLI"));
        array_yazi.add(new String("CARROT"));
        array_yazi.add(new String("PUMPKIN"));

        hayvan_isimleri = (TextView)findViewById(R.id.isimler); //isimlerin yazıldığı text i tanımlama.


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
                                Intent i = new Intent(sebzeler_learn.this, sebzeler_learn_sinav.class);
                                startActivity(i);
                                finish();
                                System.out.println("ad yüklenemedi.");
                            }

                            mInterstitialAd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    // Code to be executed when the interstitial ad is closed.
                                    Intent i = new Intent(sebzeler_learn.this, sebzeler_learn_sinav.class);
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
                Intent i = new Intent(sebzeler_learn.this, giris.class);
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
        Intent i = new Intent(sebzeler_learn.this, giris.class);
        startActivity(i);
        finish();
    }
}
