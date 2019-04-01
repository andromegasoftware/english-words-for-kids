package com.kadir.learnenglishforkids;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

public class harfler_learn_sinav extends AppCompatActivity {

    private AdView madview;
    private  int[] resim = new int[10];
    private  int[] resim_soru = new int[10];

    MediaPlayer hayvan_sesi;
    ArrayList<Integer> array_ses = new ArrayList<Integer>();

    Drawable background;

    Button ileri;
    Button anasayfa;

    ImageView imagea;
    ImageView imageb;
    ImageView imagec;
    ImageView imaged;
    Random random = new Random();

    int cevapdogru;
    Boolean kullanici_cevap = false;

    int gostera;
    int gostera_diger;
    int gosterb;
    int gosterc;
    int gosterd;

    int sik_1;
    int sik_2;
    int sik_3;
    int sik_4;

    MediaPlayer dogru;
    MediaPlayer yanlis;

    Handler h = new Handler();

    Button sonraki_soru;

    ArrayList<Integer> array_image = new ArrayList<Integer>();

    Button ses_arti;
    Button ses_eksi;

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harfler_learn_sinav);

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

        madview = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);

        //**************inersteller ad***********************
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3206398076180977/2090226859");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //******************************************************

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


        dogru = MediaPlayer.create(this, R.raw.correct);
        yanlis = MediaPlayer.create(this, R.raw.wrong);

        sonraki_soru = (Button)findViewById(R.id.sonraki_soru);


        resim = new int[]{R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g,
                R.mipmap.h, R.mipmap.i, R.mipmap.j, R.mipmap.k, R.mipmap.l, R.mipmap.m};
        resim_soru = new int[]{R.mipmap.n, R.mipmap.o, R.mipmap.p, R.mipmap.q, R.mipmap.r, R.mipmap.s, R.mipmap.t,
                R.mipmap.u, R.mipmap.v, R.mipmap.w, R.mipmap.x, R.mipmap.y, R.mipmap.z};

        array_image = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++)
        {
            array_image.add(resim[i]);
        }

        imagea = (ImageView) findViewById(R.id.imageViewa);
        imageb = (ImageView) findViewById(R.id.imageViewb);
        imagec = (ImageView) findViewById(R.id.imageViewc);
        imaged = (ImageView) findViewById(R.id.imageViewd);

        imageOyun();


        sonraki_soru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    hayvan_sesi.start();

            }
        });

        imagea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int a = imagea.getId();
                System.out.println(a);
                if(a == cevapdogru)
                {
                    dogru.start();
                    array_image.remove(gostera);
                    imagea.setEnabled(false);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(array_image.size() == 0)
                            {
                                //*******intersteller ad başlar***********
                                if (mInterstitialAd.isLoaded()) {
                                    mInterstitialAd.show();
                                } else {
                                    Intent i = new Intent(harfler_learn_sinav.this, giris.class);
                                    startActivity(i);
                                    finish();
                                    System.out.println("ad yüklenemedi.");
                                }

                                mInterstitialAd.setAdListener(new AdListener() {
                                    @Override
                                    public void onAdClosed() {
                                        // Code to be executed when the interstitial ad is closed.
                                        Intent i = new Intent(harfler_learn_sinav.this, giris.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                                //***************************
                            }
                            else
                            {
                                imageOyun();
                            }
                        }
                    }, 2000);
                }
                else
                {
                    yanlis.start();
                }

            }
        });

        imageb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int a = imageb.getId();
                System.out.println(a);
                if(a == cevapdogru)
                {
                    dogru.start();
                    array_image.remove(gostera);
                    imageb.setEnabled(false);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(array_image.size() == 0)
                            {
                                Intent i = new Intent(harfler_learn_sinav.this, giris.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                imageOyun();
                            }
                        }
                    }, 2000);
                }
                else
                {
                    yanlis.start();
                }
            }
        });

        imagec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kullanici_cevap = true;
                final int a = imagec.getId();
                System.out.println(a);
                if(a == cevapdogru)
                {
                    dogru.start();
                    array_image.remove(gostera);
                    imagec.setEnabled(false);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(array_image.size() == 0)
                            {
                                Intent i = new Intent(harfler_learn_sinav.this, giris.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                imageOyun();
                            }
                        }
                    }, 2000);
                }
                else
                {
                    yanlis.start();
                }
            }
        });

        imaged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int a = imaged.getId();
                System.out.println(a);
                if(a == cevapdogru)
                {
                    dogru.start();
                    array_image.remove(gostera);
                    imaged.setEnabled(false);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(array_image.size() == 0)
                            {
                                Intent i = new Intent(harfler_learn_sinav.this, giris.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                imageOyun();
                            }
                        }
                    }, 2000);
                }
                else
                {
                    yanlis.start();
                }
            }
        });

        anasayfa = (Button)findViewById(R.id.anasayfa);
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(harfler_learn_sinav.this, activity_giris_iki.class);
                startActivity(i);
                finish();
            }
        });
    }

    public  void imageOyun()
    {
        imagea.setEnabled(true);
        imageb.setEnabled(true);
        imagec.setEnabled(true);
        imaged.setEnabled(true);

        sonraki_soru.setBackgroundResource(R.mipmap.hoparlor);
        background = sonraki_soru.getBackground();

        gostera = random.nextInt(array_image.size());
        gostera_diger = random.nextInt(resim_soru.length);
        if(gostera_diger >= 6)
        {
            gosterb = gostera_diger - 1;
            gosterc = gostera_diger - 2;
            gosterd = gostera_diger - 3;
        }
        else
        {
            gosterb = gostera_diger + 1;
            gosterc = gostera_diger + 2;
            gosterd = gostera_diger + 3;
        }

        sik_1 = random.nextInt(4) + 1;
        sik_2 = random.nextInt(4) + 1;
        while (sik_1 == sik_2)
        {
            sik_2 = random.nextInt(4) + 1;
        }
        sik_3 = random.nextInt(4) + 1;
        while (sik_1 == sik_3 || sik_2 == sik_3)
        {
            sik_3 = random.nextInt(4) + 1;
        }
        sik_4 = random.nextInt(4) + 1;
        while (sik_1 == sik_4 || sik_2 == sik_4 || sik_3 == sik_4)
        {
            sik_4 = random.nextInt(4) + 1;
        }

        switch (sik_1)
        {
            case 1: imagea.setImageResource(array_image.get(gostera));
                cevapdogru = imagea.getId();
                System.out.println(cevapdogru);

                hayvan_sesi = MediaPlayer.create(this, array_ses.get(gostera));
                hayvan_sesi.start();
                array_ses.remove(gostera);
                break;
            case 2: imageb.setImageResource(array_image.get(gostera));
                cevapdogru = imageb.getId();
                System.out.println(cevapdogru);

                hayvan_sesi = MediaPlayer.create(this, array_ses.get(gostera));
                hayvan_sesi.start();
                array_ses.remove(gostera);
                break;
            case 3: imagec.setImageResource(array_image.get(gostera));
                cevapdogru = imagec.getId();
                System.out.println(cevapdogru);

                hayvan_sesi = MediaPlayer.create(this, array_ses.get(gostera));
                hayvan_sesi.start();
                array_ses.remove(gostera);
                break;
            case 4: imaged.setImageResource(array_image.get(gostera));
                cevapdogru = imaged.getId();
                System.out.println(cevapdogru);

                hayvan_sesi = MediaPlayer.create(this, array_ses.get(gostera));
                hayvan_sesi.start();
                array_ses.remove(gostera);
                break;
        }
        switch (sik_2)
        {
            case 1: imagea.setImageResource(resim_soru[gosterb]); break;
            case 2: imageb.setImageResource(resim_soru[gosterb]); break;
            case 3: imagec.setImageResource(resim_soru[gosterb]); break;
            case 4: imaged.setImageResource(resim_soru[gosterb]); break;
        }
        switch (sik_3)
        {
            case 1: imagea.setImageResource(resim_soru[gosterc]); break;
            case 2: imageb.setImageResource(resim_soru[gosterc]); break;
            case 3: imagec.setImageResource(resim_soru[gosterc]); break;
            case 4: imaged.setImageResource(resim_soru[gosterc]); break;
        }
        switch (sik_4)
        {
            case 1: imagea.setImageResource(resim_soru[gosterd]); break;
            case 2: imageb.setImageResource(resim_soru[gosterd]); break;
            case 3: imagec.setImageResource(resim_soru[gosterd]); break;
            case 4: imaged.setImageResource(resim_soru[gosterd]); break;
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(harfler_learn_sinav.this, giris.class);
        startActivity(i);
        finish();

    }
}
