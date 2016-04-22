package com.example.admin.instacoder;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by dimsob on 08.04.16.
 */
public class ImageGridAdapter extends ArrayAdapter<ImageItem> {
    private Context context;
    private Activity activity;
    private int layoutResourceId;
    private ArrayList<ImageItem> data = new ArrayList<ImageItem>();
    private static LayoutInflater inflater = null;
    Bitmap b;

    public ImageGridAdapter(Activity a, Context context, int layoutResourceId,
                            ArrayList<ImageItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        activity = a;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {

            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageItem item = data.get(position);
        holder.image.setImageBitmap(item.getImage());
        row.setId(position);

        Log.d("testtest1", String.valueOf(holder.image.getId()));
        Log.d("testtest2", String.valueOf(holder.image.getTag()));
        Log.d("testtest3", String.valueOf(holder.image.getDrawingCache()));
        row.setOnClickListener(new OnItemClickListener(position, String.valueOf(holder.image.getId())));
        return row;
    }

    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;
        private String image_t;

        OnItemClickListener(int position, String image_tt) {
            mPosition = position;
            image_t = image_tt;

        }

        @Override
        public void onClick(View arg0) {

            Intent myIntent = new Intent(context, AddImageActivity.class);

            // your bitmap
            ImageItem item = getItem(mPosition);

            myIntent.putExtra("ImageBitmap", item.getImage());
            myIntent.putExtra("position", String.valueOf(mPosition));
            myIntent.putExtra("image_tt", item.getImage());
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent);

        }
    }

    static class ViewHolder {
        ImageView image;
    }
}