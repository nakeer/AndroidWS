package nkeerthy.org.simpletodo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {


    ArrayList<String> items;
    ArrayList<String> items_db;
    ArrayAdapter<String> itemsAdapter;
    ArrayAdapter<String> itemsAdapter_db;
    ListView lvItems;
    private final int REQUEST_CODE = 20;
    public static int id = 0;

    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items_db = mydb.getAllItems();
        itemsAdapter_db = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items_db);

        lvItems.setAdapter(itemsAdapter_db);

        setupListViewListener();

    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                    items_db.remove(pos);
                    Log.d("****DEBUGGER:","Position: " + pos);
                    itemsAdapter_db.notifyDataSetChanged();
                    Log.d("****DEBUGGER:","BEFORE DELETION ");
                    showDB();
                    mydb.deleteToDoItem(pos);
                    Log.d("****DEBUGGER:","AFTER DELETION ");
                    showDB();
                    return true;
                }
            }
        );

        lvItems.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                    i.putExtra("itemText",items_db.get(position));
                    //Sending the position value as RequestCode to make it easier to get it OnAcitivityResult
                    startActivityForResult(i, position);
                }


            }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data ) {
        if (resultCode == RESULT_OK ) {
            String newEditText = data.getExtras().getString("newEditedData");
            Log.d("SimpleToDoApp", "*************Request code: " + requestCode + " with Data: " + newEditText);
            items_db.set(requestCode, newEditText);
            itemsAdapter_db.notifyDataSetChanged();
            mydb.updateToDoItem(requestCode, newEditText, null, null);
        }
    }

    public void showDB() {
       ArrayList<String> list = mydb.getAllItemsWithIds();
       for( String item : list){
           Log.d("DB: ", item);
       }

    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter_db.add(itemText);
        etNewItem.setText("");
        mydb.insertToDoItem(itemText, "default", "no");
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
