package daniel.brian.dairyfarmapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PoultryFeedingDB extends SQLiteOpenHelper {
    public PoultryFeedingDB(@Nullable Context context) {
        super(context, "PoultryFeeding.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("Create Table PoultryFeeding(" +
              "Category_Fed varchar(10) not null," +
              "Feed_Type varchar(10) not null," +
              "Feed_AM varchar(10) not null,"+
              "Feed_Noon varchar(10) not null,"+
              "Feed_PM varchar(10) not null,"+
              "Feed_Person varchar(10) not null,"+
              "Feed_Quantity DATE not null," +
              "Feed_Cost DATE not null," +
              "Feed_Purchase_Date DATE not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("drop Table if exists PoultryFeeding");
    }

    // function to save breeding records
    public boolean SavePoultryFeedingRecords(String category, String feed,String feed_am,String feed_noon,String feed_pm,String feedPerson, String quantity, String cost, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Category_Fed",category);
        contentValues.put("Feed_Type",feed);
        contentValues.put("Feed_AM",feed_am);
        contentValues.put("Feed_Noon",feed_noon);
        contentValues.put("Feed_PM",feed_pm);
        contentValues.put("Feed_Person",feedPerson);
        contentValues.put("Feed_Quantity",quantity);
        contentValues.put("Feed_Cost",cost);
        contentValues.put("Feed_Purchase_Date",date);
        long result = db.insert("PoultryFeeding",null,contentValues);
        return result != -1;
    }

    public boolean CheckRedundantData(String category,String age){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("select * from PoultryFeeding where Category_Fed = ? and Feed_Purchase_Date = ?",new String[]{category,age});
        return cursor.getCount() > 0;
    }
}
