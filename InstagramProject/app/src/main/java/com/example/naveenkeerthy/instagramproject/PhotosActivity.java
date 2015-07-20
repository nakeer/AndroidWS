package com.example.naveenkeerthy.instagramproject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PhotosActivity extends ActionBarActivity {

    public static final String CLIENT_ID = "1daf80c42d4c4c7fbaae912bfb96e503";
    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotosAdapter instagramPhotosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        photos = new ArrayList<>();

        //1. create the adapter linking to the source
        instagramPhotosAdapter = new InstagramPhotosAdapter(this, photos);
        //2. Find the list view from the layout and set the adapter binding it to the listview
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        //3. Set the adapter binding it to the listView
        lvPhotos.setAdapter(instagramPhotosAdapter);
        //Fethc the popular photos
        fetchPopularPhotos();
    }

    //Trigger API request
    public void fetchPopularPhotos() {

    /*
    - Popular: https://api.instagram.com/v1/media/popular?access_token=ACCESS-TOKEN
     - Response :

     */
        //creating the network client
        String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        //Trigger the GET request
        asyncHttpClient.get(url,null, new JsonHttpResponseHandler() {
            //onsuccess 200

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Expecting a JSON Object
                /*
                - Type :{ “data” => [x] => “type”} (“image” or “video”)
                - URL :{ “data” => [x] => “images” => “standard_resolution”=>”url"}
                - Caption :{ “data” => [x] => “caption” => “text"}
                - AuthorName: { “data” => [x] => “user” => “username"}
                 */

                Log.i("DEBUG", response.toString());
                JSONArray photosJSON = null;

                try{
                    photosJSON = response.getJSONArray("data"); //array of posts
                    //iterate array of posts
                    for(int i=0;i<photosJSON.length();i++){
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        InstagramPhoto photo = new InstagramPhoto();
                        photo.userName = photoJSON.getJSONObject("user").getString("username");
                        photo.imageUrl = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        photo.imageHeight = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        photo.likesCount = photoJSON.getJSONObject("likes").getInt("count");
                        photo.caption = photoJSON.getJSONObject("caption").getString("text");
                        photos.add(photo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //callback
                instagramPhotosAdapter.notifyDataSetChanged();
            }


            //onfailure


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                //DO SOMETHING
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
