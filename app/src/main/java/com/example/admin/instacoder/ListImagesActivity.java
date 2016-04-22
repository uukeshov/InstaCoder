package com.example.admin.instacoder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ListImagesActivity extends AppCompatActivity {

    String LOG_TAG = "myinstaLog";
    ImageGridAdapter iAdapter;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_images);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAdView = (AdView) findViewById(R.id.ad_view);
        Log.d(LOG_TAG, "Start advertisment");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
        Log.d(LOG_TAG, "Finish advetisment");
    }
}
