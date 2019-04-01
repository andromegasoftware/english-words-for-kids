package com.kadir.learnenglishforkids;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class acilis_sayfasi extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acilis_sayfasi);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(acilis_sayfasi.this, giris.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
