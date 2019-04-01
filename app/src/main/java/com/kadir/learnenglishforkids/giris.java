package com.kadir.learnenglishforkids;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import static com.kadir.learnenglishforkids.R.mipmap.audiono;

public class giris extends AppCompatActivity {

    Button animals;
    Button numbers;
    Button meyveler;
    Button araclar;
    Button sebzeler;
    Button renkler;
    Button meslekler;
    Button organlar;
    Button giris_ikiye_git;
    Button exit;
    MediaPlayer media;
    Button ses_arti;
    Button ses_eksi;
    Button hoparlor;

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        MobileAds.initialize(this, "ca-app-pub-3206398076180977~1188508498"); //reklamı initialise etmek için
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3206398076180977/2090226859");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        media = MediaPlayer.create(this, R.raw.music);
        media.start();

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

        animals = (Button)findViewById(R.id.animals);
        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(giris.this, animals_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(giris.this, animals_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        numbers = (Button)findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(giris.this, numbers_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(giris.this, numbers_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        meyveler = (Button)findViewById(R.id.fruits);
        meyveler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(giris.this, meyveler_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(giris.this, meyveler_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        araclar = (Button)findViewById(R.id.vehicals);
        araclar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(giris.this, araclar_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(giris.this, araclar_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        sebzeler = (Button)findViewById(R.id.vegatabels);
        sebzeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(giris.this, sebzeler_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(giris.this, sebzeler_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        renkler = (Button)findViewById(R.id.colors);
        renkler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(giris.this, colors_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(giris.this, colors_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        meslekler = (Button)findViewById(R.id.jobs);
        meslekler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(giris.this, meslekler_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(giris.this, meslekler_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        organlar = (Button)findViewById(R.id.vucut);
        organlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    media.stop();
                    mInterstitialAd.show();
                } else {
                    System.out.println("ad yüklenemedi.");
                    Intent i  = new Intent(giris.this, organlar_learn.class);
                    startActivity(i);
                    media.stop();
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i  = new Intent(giris.this, organlar_learn.class);
                        startActivity(i);
                        media.stop();
                        finish();
                    }
                });
            }
        });

        giris_ikiye_git = (Button)findViewById(R.id.giris_ikiye_git);
        giris_ikiye_git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(giris.this, activity_giris_iki.class);
                startActivity(i);
                media.stop();
                finish();
            }
        });

        exit = (Button)findViewById(R.id.exit2);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(giris.this);
                alert.setTitle(R.string.cik);
                alert.setMessage(R.string.soru);
                alert.setCancelable(true);

                alert.setNegativeButton(R.string.hayir, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });

                alert.setPositiveButton(R.string.evet, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        media.stop();
                        finish();
                    }
                });

                AlertDialog al = alert.create();
                al.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(giris.this);
        alert.setTitle(R.string.cik);
        alert.setMessage(R.string.soru);
        alert.setCancelable(true);

        alert.setNegativeButton(R.string.hayir, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });

        alert.setPositiveButton(R.string.evet, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                media.stop();
                finish();
            }
        });

        AlertDialog al = alert.create();
        al.show();
        finish();
    }
    @Override
    protected void onPause() {
        media.pause();
        super.onPause();
    }

}
