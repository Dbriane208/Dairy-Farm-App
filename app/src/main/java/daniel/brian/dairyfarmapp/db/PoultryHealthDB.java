package daniel.brian.dairyfarmapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PoultryHealthDB extends SQLiteOpenHelper {
    public PoultryHealthDB(@Nullable Context context) {
        super(context, "PoultryHealth.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table PoultryHealth(" +
                "Category_Affected varchar(10) not null," +
                "Disease_Name varchar(20) not null," +
                "Vaccine_Name varchar(50) not null," +
                "Date DATE not null," +
                "Birds_Affected varchar(50) not null," +
                "Cost_of_Treatment Text not null," +
                "VetName varchar(10) not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("drop Table if exists PoultryHealth");
    }

    // saving the cows health Details
    public boolean SaveCowHealthRecords(String category, String disease, String vaccine, String date, String birdsAffected, String costOfTreatment, String vetName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Category_Affected",category);
        contentValues.put("Disease_Name",disease);
        contentValues.put("Vaccine_Name",vaccine);
        contentValues.put("Date",date);
        contentValues.put("Birds_Affected",birdsAffected);
        contentValues.put("Cost_of_Treatment",costOfTreatment);
        contentValues.put("VetName",vetName);
        long result = db.insert("PoultryHealth",null,contentValues);
        return result != -1;
    }

    public boolean CheckRedundantData(String category,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("select * from PoultryHealth where Category_Affected = ? and Date = ?",new String[]{category,date});
        return cursor.getCount() > 0;
    }
}
