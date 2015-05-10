package nkeerthy.org.simpletodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by naveen.keerthy on 3/29/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TodoDB.db";
    public static final String TODO_TABLE_NAME = "todoListTable";
    public static final String TODO_COLUMN_ID = "id";
    public static final String TODO_COLUMN_NAME = "todo";
    public static final String TODO_COLUMN_STATUS = "status";
    public static final String TODO_COLUMN_REMAINDER = "remainder";

    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+TODO_TABLE_NAME+" ("+TODO_COLUMN_ID+" integer primary key, "+TODO_COLUMN_NAME+" text,"+TODO_COLUMN_STATUS+" text,"+TODO_COLUMN_REMAINDER+" text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TODO_TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<String> getAllItems() {

        ArrayList todoList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from "+TODO_TABLE_NAME, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            todoList.add(res.getString(res.getColumnIndex(TODO_COLUMN_NAME)));
            res.moveToNext();
        }
        return todoList;
    }

    public ArrayList<String> getAllItemsWithIds() {

        ArrayList todoList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from "+TODO_TABLE_NAME, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            todoList.add(res.getString(res.getColumnIndex(TODO_COLUMN_ID)));
            res.moveToNext();
        }
        return todoList;
    }

    public Integer deleteToDoItem(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TODO_TABLE_NAME, ""+TODO_COLUMN_ID+" = ?", new String[] { Integer.toString(id) });
    }

    public boolean insertToDoItem(String todoItem, String status, String remainder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("todo", todoItem);
        contentValues.put("status", status);
        contentValues.put("remainder",remainder);
        db.insert(TODO_TABLE_NAME, null, contentValues );
        return true;
    }

    public boolean updateToDoItem(Integer id, String todoItem, String status, String remainder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("todo", todoItem);
        contentValues.put("status", status);
        contentValues.put("remainder",remainder);
        db.update(TODO_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TODO_TABLE_NAME);
        return numRows;
    }

    public Cursor getToDoItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from "+TODO_TABLE_NAME+" where id="+id+"", null);
        return res;
    }




}
