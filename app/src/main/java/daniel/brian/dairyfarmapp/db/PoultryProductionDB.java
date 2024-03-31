package daniel.brian.dairyfarmapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PoultryProductionDB extends SQLiteOpenHelper {
    public PoultryProductionDB(@Nullable Context context) {
        super(context, "PoultryProduction.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table PoultryProduction("+
                "Batch_Name varchar(10) not null,"+
                "Meat_Prod varchar(10) not null,"+
                "Meat_Person varchar(10) not null,"+
                "Meat_Time varchar(10) not null,"+
               "Collection_Area varchar(10) not null," +
               "Collection_Date DATE not null," +
               "Collected_Eggs Text not null," +
               "Good_Eggs Text not null," +
               "Broken_Eggs Text not null," +
               "Trays_Collected Text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("drop Table if exists PoultryProduction");
    }

    // saving the milk details
    public boolean savePoultryProductionDetails(String batch,String meatProd,String meatPerson,String time,String collectionArea, String collectionDate, String collectedEggs, String goodEggs, String brokenEggs, String traysCollected){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Batch_Name",batch);
        contentValues.put("Meat_Prod",meatProd);
        contentValues.put("Meat_Person",meatPerson);
        contentValues.put("Meat_Time",time);
        contentValues.put("Collection_Area",collectionArea);
        contentValues.put("Collection_Date",collectionDate);
        contentValues.put("Collected_Eggs",collectedEggs);
        contentValues.put("Good_Eggs",goodEggs);
        contentValues.put("Broken_Eggs",brokenEggs);
        contentValues.put("Trays_Collected",traysCollected);
        long result = db.insert("PoultryProduction",null,contentValues);
        return result != -1;
    }

    public boolean CheckRedundantData(String area,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("select * from PoultryProduction where Collection_Area = ? and Collection_Date = ?",new String[]{area,date});
        return cursor.getCount() > 0;
    }
}
