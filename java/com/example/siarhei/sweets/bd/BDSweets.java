package com.example.siarhei.sweets.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.siarhei.sweets.R;
import com.example.siarhei.sweets.Sweet;

import java.util.ArrayList;
import java.util.List;


public class BDSweets extends SQLiteOpenHelper {

    private static final String LOG_TAG = "sweetsLogs";
    private List<Long> allSweets = new ArrayList<Long>();
    private List<Long> selectedSweets = new ArrayList<Long>();


    public BDSweets(Context context) {
        super(context,"DB",null,8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOG_TAG, "--- onCreate database ---");
        db.execSQL("create table assortiment(id integer primary key autoincrement, name text, icon integer, info integer, price real);");
        db.execSQL("create table selectedSweets(id integer primary key autoincrement, name text, icon integer, info integer, price real);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(LOG_TAG, "--- onUpdate database ---");

       /* db.execSQL("drop table typesOfSweets;");
        db.execSQL("drop table assortiment;");
        db.execSQL("drop table selectedSweets;");
        db.execSQL("create table typesOfSweets(cid integer primary key autoincrement, name text);");
        db.execSQL("insert into typesOfSweets(name) values ('Cookies');");
        db.execSQL("insert into typesOfSweets(name) values ('Sweets');");
        db.execSQL("create table assortiment(id integer primary key autoincrement, name text, icon integer, info integer, price real, typesofsw int,Constraint tys FOREIGN KEY ( typesofsw) REFERENCES typesOfSweets(cid));");
        db.execSQL("create table selectedSweets(id integer primary key autoincrement, name text, icon integer, info integer, price real,typesofsw int,Constraint tys FOREIGN KEY ( typesofsw) REFERENCES typesOfSweets(cid));");
      //  db.execSQL("ALTER TABLE assortiment ADD Constraint typesofsw FOREIGN KEY (cid) REFERENCES typesOfSweets(cid)");*/
        //db.execSQL("ALTER TABLE assortiment ADD FOREIGN KEY (P_id) REFERENCES typesOfSweets(id);");
        db.execSQL("UPDATE assortiment SET typesofsw = 1 WHERE id < 4;");
        db.execSQL("UPDATE assortiment SET typesofsw = 2 WHERE id >= 4; ");


    }


    public List<Long> getAllSweets() { return allSweets; }

    public List<Long> getSelectedSweets() { return selectedSweets; }


    public void deleteSweet(SQLiteDatabase db, int id){
       int delCount = db.delete("selectedSweets", "id = " + id, null);
        Log.i(LOG_TAG,  ": Deleted.---------");
    }

    public void addSweet(SQLiteDatabase db, Sweet addedSweet){
        ContentValues cv = new ContentValues();
        cv.put("name",addedSweet.getName());
        cv.put("icon",addedSweet.getIconId());
        cv.put("info",addedSweet.getExtraInfo());
        cv.put("price",addedSweet.getPrice());
        cv.put("typesofsw",addedSweet.getTypeOfSweet());
        selectedSweets.add(db.insert("assortiment", null, cv));
        Log.i(LOG_TAG, ": Added.---------");
    }


}
