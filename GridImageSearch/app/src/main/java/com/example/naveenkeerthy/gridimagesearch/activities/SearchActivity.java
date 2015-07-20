package com.example.naveenkeerthy.gridimagesearch.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.example.naveenkeerthy.gridimagesearch.adapters.ImageResultsAdapter;
import com.example.naveenkeerthy.gridimagesearch.models.ImageResult;
import com.example.naveenkeerthy.gridimagesearch.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchActivity extends ActionBarActivity {

    private EditText etQuery;
    private GridView gvResults;
    private ArrayList<ImageResult> imageResults;
    private ImageResultsAdapter aImageResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews(); //This is responsible for getting the views

        //Create the data source
        imageResults = new ArrayList<ImageResult>();

        // Attaches the data source to an adapter
        aImageResults = new ImageResultsAdapter(this, imageResults);

        //link the adapter to the adpterview (gridview)
        gvResults.setAdapter(aImageResults);


    }

    private void setupViews() {
        //Whenver I want to locate a view from a template associated to an activity. Specify the selector id.
        etQuery = (EditText) findViewById(R.id.etQuery); //This is how we access views from the xml
        gvResults = (GridView) findViewById(R.id.gvResults);
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Launce the imagedisplay activity
                //creating an intent
                Intent i = new Intent(SearchActivity.this, ImageDisplayActivity.class);
                //Get the image result to displlay
                ImageResult result = imageResults.get(position);
                //Pass the image result inot the intent
                i.putExtra("result", result);
                //Launch the new activity
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    //Fired whenever the button is pressed (android:onclick property)
    /*
    In addition to the view v, we need a handle to the text and the grid view. So get references for the template
    So declare them as private variables, eg: etQuery and gvResults
    then setup the views
     */
    public void onImageSearch(View v) {
        String query = etQuery.getText().toString();

        /*
        Toast.makeText(this, "Search for: " +query, Toast.LENGTH_LONG).show(); //Toast is simple alert just like
        // java script alert, will show the string on the screen and will go off
        */

        //create our client
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        String searchUrl = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q="+ query +"&rsz=8";

        asyncHttpClient.get(searchUrl, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", response.toString());
                JSONArray imageResultsJson = null;
                try {
                    imageResultsJson = response.getJSONObject("responseData").getJSONArray("results");
                    imageResults.clear(); //clear the existing fromt hte array in case where its a new search.

                    /*
                    Here we can just notify the adapterview and force set the changes in the dataset

                    imageResults.addAll(ImageResult.fromJSONArray(imageResultsJson));
                    aImageResults.notifyDataSetChanged();

                     */

                    //whereas we can also try the below instead of just notifying the DS changes to the adpter.
                    //we can directly add it to the adapterlist instead of adding it to ArrayList, adapterlist has all the methods
                    // present in the array List. It will also notify

                    aImageResults.addAll(ImageResult.fromJSONArray(imageResultsJson));

                } catch (JSONException e) {

                }
                Log.i("INFO", imageResults.toString());
            }
        });
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
