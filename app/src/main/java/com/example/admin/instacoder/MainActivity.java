package com.example.admin.instacoder;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    String LOG_TAG = "myinstaLog";
    ImageGridAdapter iAdapter;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdView = (AdView) findViewById(R.id.ad_view);
        Log.d(LOG_TAG, "Start advertisment");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
        Log.d(LOG_TAG, "Finish advetisment");
    }

    @Override
    protected void onStart() {
        super.onStart();

        GridView gv1 = (GridView) findViewById(R.id.companyGridView);
        gv1.setAdapter(new ImageGridAdapter(MainActivity.this, getApplicationContext(),
                R.layout.row_grid, getData()));

        Log.d(LOG_TAG, "myinstaLog1" + String.valueOf(R.drawable.android2));
/*
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if (position == 0) {
                    Intent i = new Intent(
                            getApplicationContext(),
                            ListImagesActivity.class);
                    int im = R.drawable.android2;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addImage) {

            Intent intent = new Intent(this, AddImageActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.listmage) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
