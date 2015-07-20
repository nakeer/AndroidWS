package com.example.naveenkeerthy.gridimagesearch.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by naveen.keerthy on 7/19/15.
 * This is a model class, so most of the object searializatio, deserialization logic should happen here
 */
public class ImageResult implements Serializable{

    private static final long serialVersionUID = -6211037581619200339L;
    public String fullUrl;
    public String thumbUrl;
    public String title;


    // Go to week 3 JSON Model concepts for more information about the two models below
    // new ImageResult(..raw item json..)
    public ImageResult(JSONObject json) {
        try {
            this.fullUrl = json.getString("url");
            this.thumbUrl = json.getString("tbUrl");
            this.title = json.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Take an array of Json images and return arraylist of image results
    // ImageResult from JSONArray([...,...])
    public static ArrayList<ImageResult> fromJSONArray(JSONArray array) {
        ArrayList<ImageResult> results = new ArrayList<ImageResult>();
        for (int i=0;i<array.length(); i++) {
            try {
                results.add(new ImageResult(array.getJSONObject(i)));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  results;
    }
}
