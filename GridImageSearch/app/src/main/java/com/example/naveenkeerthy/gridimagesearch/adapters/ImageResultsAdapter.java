package com.example.naveenkeerthy.gridimagesearch.adapters;

import android.content.Context;
import android.media.Image;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naveenkeerthy.gridimagesearch.R;
import com.example.naveenkeerthy.gridimagesearch.models.ImageResult;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by naveen.keerthy on 7/19/15.
 */
public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {
    public ImageResultsAdapter(Context context, List<ImageResult> images) {
        super(context, android.R.layout.simple_list_item_1, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageResult imageInfo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);

        }
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

        //clear out the image from the last view
        ivImage.setImageResource(0);
        // populate title and remote download image url
        tvTitle.setText(Html.fromHtml(imageInfo.title));

        Picasso.with(getContext()).load(imageInfo.thumbUrl).into(ivImage);
        //Return the completed view to be displayed
        return convertView;
    }
}
