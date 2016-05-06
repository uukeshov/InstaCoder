package com.example.admin.instacoder;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

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
                .addTestDevice("820DF03930E923D579F0E0C77AD7C340")
                .build();
        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
        Log.d(LOG_TAG, "Finish advetisment");
    }

    @Override
    protected void onStart() {
        super.onStart();

        ListView gv1 = (ListView) findViewById(R.id.companyListView);
        gv1.setAdapter(new ImageGridAdapter(this, getApplicationContext(),
                R.layout.row_grid, getData()));

/*
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if (position == 0) {
                    Intent i = new Intent(
                            getApplicationContext(),
                            ListImagesActivity.class);
                    int im = R.drawable.pic1;
                    i.putExtra("imageid", im);

                    startActivity(i);
                }

            }
        });*/
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<ImageItem>();
        // retrieve String drawable array
        TypedArray imgs = getResources().obtainTypedArray(R.array.images);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                    imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap));
        }

        return imageItems;

    }
}
