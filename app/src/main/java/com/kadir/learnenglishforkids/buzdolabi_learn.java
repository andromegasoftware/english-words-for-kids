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

public class buzdolabi_learn extends AppCompatActivity {

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
        setContentView(R.layout.activity_buzdolabi_learn);

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

        array_image.add(R.mipmap.ekme);
        array_image.add(R.mipmap.yumurta);
        array_image.add(R.mipmap.peynir);
        array_image.add(R.mipmap.zeytin);
        array_image.add(R.mipmap.kah);
        array_image.add(R.mipmap.portakalsuyu);
        array_image.add(R.mipmap.su);
        array_image.add(R.mipmap.recel);
        array_image.add(R.mipmap.sut);
        array_image.add(R.mipmap.tereyagi);


        array_ses.add(R.raw.ekmek);
        array_ses.add(R.raw.yumurta);
        array_ses.add(R.raw.peynir);
        array_ses.add(R.raw.zeytin);
        array_ses.add(R.raw.kahve);
        array_ses.add(R.raw.portakalsuyu);
        array_ses.add(R.raw.su);
        array_ses.add(R.raw.recel);
        array_ses.add(R.raw.sut);
        array_ses.add(R.raw.tereyagi);

        array_yazi.add(new String("BREAD")); // isimleri diziye ekleme
        array_yazi.add(new String("EGG"));
        array_yazi.add(new String("CHEESE"));
        array_yazi.add(new String("OLIVE"));
        array_yazi.add(new String("CAFFEE"));
        array_yazi.add(new String("ORANGE JUICE"));
        array_yazi.add(new String("WATER"));
        array_yazi.add(new String("JAM"));
        array_yazi.add(new String("MILK"));
        array_yazi.add(new String("BUTTER"));


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
                                Intent i = new Intent(buzdolabi_learn.this, buzdolabi_learn_sinav.class);
                                startActivity(i);
                                finish();
                                System.out.println("ad yüklenemedi.");
                            }

                            mInterstitialAd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    // Code to be executed when the interstitial ad is closed.
                                    Intent i = new Intent(buzdolabi_learn.this, buzdolabi_learn_sinav.class);
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
                Intent i = new Intent(buzdolabi_learn.this, activity_giris_iki.class);
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
        Intent i = new Intent(buzdolabi_learn.this, giris.class);
        startActivity(i);
        finish();
    }
}