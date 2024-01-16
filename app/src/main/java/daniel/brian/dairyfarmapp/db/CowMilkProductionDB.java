package daniel.brian.dairyfarmapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CowMilkProductionDB extends SQLiteOpenHelper {
    public CowMilkProductionDB(@Nullable Context context) {
        super(context, "cowsMilkProduction.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table cowMilkProduction("+
               "CowMilk_id integer primary key not null," +
               "Name varchar(10) not null," +
               "AM_milk Text not null," +
               "Noon_milk Text not null," +
               "PM_milk Text not null," +
               "Total Text not null," +
               "Date DATE not null," +
               "constraint milkProduction unique(CowMilk_id,Name))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("drop Table if exists cowMilkProduction");
    }

    // saving the milk details
    public boolean saveMilkProductionDetails(String name,String am_milk,String noon_milk,String pm_milk,String total,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("AM_milk",am_milk);
        contentValues.put("Noon_milk",noon_milk);
        contentValues.put("PM_milk",pm_milk);
        contentValues.put("Total",total);
        contentValues.put("Date",date);
        long result = db.insert("cowMilkProduction",null,contentValues);
        return result != -1;
    }
}
