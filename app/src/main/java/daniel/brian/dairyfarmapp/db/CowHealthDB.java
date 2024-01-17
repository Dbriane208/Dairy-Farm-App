package daniel.brian.dairyfarmapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CowHealthDB extends SQLiteOpenHelper {
    public CowHealthDB(@Nullable Context context) {
        super(context, "CowsHealth.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table cowHealth(" +
                "CowHealth_id integer primary key not null," +
                "Name varchar(10) not null," +
                "Event varchar(20) not null," +
                "Diagnosis varchar(50) not null," +
                "Date DATE not null," +
                "Treatment varchar(50) not null," +
                "Cost_of_Treatment Text not null," +
                "VetName varchar(10) not null," +
                "constraint cowHealth unique(CowHealth_id,Name))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("drop Table if exists cowHealth");
    }

    // saving the cows health Details
    public boolean SaveCowHealthRecords(String name,String event,String diagnosis,String date,String treatment,String costOfTreatment,String vetName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("Event",event);
        contentValues.put("Diagnosis",diagnosis);
        contentValues.put("Date",date);
        contentValues.put("Treatment",treatment);
        contentValues.put("Cost_of_Treatment",costOfTreatment);
        contentValues.put("VetName",vetName);
        long result = db.insert("cowHealth",null,contentValues);
        return result != -1;
    }
}
