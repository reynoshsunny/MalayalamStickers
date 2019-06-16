package com.example.samplestickerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.samplestickerapp.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.reynosh.stickers.AboutActivity;
import com.reynosh.stickers.WebActivity;


public class OpenActivity extends AppCompatActivity {

    Button starthelp, startadding, startshare, startabout, check, request_new;

    private AdView mAdMobAdView;
    InterstitialAd mAdMobInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        //getting the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle("Malayalam Stickers");

      /*  //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);*/
        setSupportActionBar(toolbar);

        startadding = findViewById(R.id.startadding);
        startshare = findViewById(R.id.startshare);
        startabout = findViewById(R.id.startabout);
        check = findViewById(R.id.check);
        request_new = findViewById(R.id.request_new);

    /*    // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-9577009223941910~3716165459");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9577009223941910/8310016588");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
*/
       /* ca-app-pub-9577009223941910/9149499112*/

        mAdMobAdView = (AdView) findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
             /*   .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("Device ID")// Add your real device id here*/
                .build();
        mAdMobAdView.loadAd(adRequest);






        startadding.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent ii = new Intent(OpenActivity.this, EntryActivity.class);
                startActivity(ii);
            }
        });
        startshare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello Check this Cool App for sending stickers on WhatsApp \n\n  https://play.google.com/store/apps/details?id=com.reynosh.stickers    \n\n By Team *HashLoop*");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        startabout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent ii = new Intent(OpenActivity.this, AboutActivity.class);
                startActivity(ii);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    Intent appStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.reynosh.stickers"));
                    appStoreIntent.setPackage("com.android.vending");

                    startActivity(appStoreIntent);
                } catch (android.content.ActivityNotFoundException exception) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.reynosh.stickers")));
                }
            }
        });
        request_new.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                String[] TO = {"info@hashloop.in"};

                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Request For Stickers");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello HashLoop !!Please Add  ");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });


        mAdMobInterstitialAd = new InterstitialAd(this);
        mAdMobInterstitialAd.setAdUnitId("ca-app-pub-9577009223941910/8310016588");
        AdRequest adRequests = new AdRequest.Builder()
             /*   .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("ad device")// Add your real device id here*/
                .build();

        mAdMobInterstitialAd.loadAd(adRequests);
        mAdMobInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitialAd();
            }
        });


    }

    private void showInterstitialAd() {
        if (mAdMobInterstitialAd.isLoaded()) {
            mAdMobInterstitialAd.show();
        }
    }

}
