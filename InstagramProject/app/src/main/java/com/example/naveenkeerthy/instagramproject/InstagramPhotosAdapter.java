package com.example.naveenkeerthy.instagramproject;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by naveen.keerthy on 5/2/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {
    //what data do we need from the activity
    //Context, Data Source
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    //what our item looks like
    //use the template to display each photo
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get the data item for this posistion
        InstagramPhoto photo = getItem(position);
        //Check if we are using a recycled view, if not we need to inflate
        if(convertView == null) {
            //create a new view from template
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }
        //Lookup the views for pupulating the data (image, caption)
        TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhoto);
        //Inster the model data into each of the items
        tvCaption.setText(photo.caption);
        //Insert the image aswell but using picasso: But this actually takes sime time as we need to get the image data from the URL
        //and load it into image view. So there will be a background request for getting the image and while we are waiting we need to
        //clear the image view
        ivPhoto.setImageResource(0);
        //Insert the image using picsso
        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto);
        //Return the created item as a view
        return convertView;

    }
}
