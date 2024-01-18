package daniel.brian.dairyfarmapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CowBreedingDB extends SQLiteOpenHelper {
    public CowBreedingDB(@Nullable Context context) {
        super(context, "CowsBreeding.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("Create Table cowBreeding(" +
              "CowBreed_id integer primary key not null," +
              "Name varchar(10) not null," +
              "Age varchar(10) not null," +
              "Breed_date DATE not null," +
              "Pregnancy_date DATE not null," +
              "Date_calved DATE not null," +
              "constraint cowBreed unique (CowBreed_id,Name,Age))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("drop Table if exists cowBreeding");
    }

    // function to save breeding records
    public boolean SaveCowBreedingRecords(String name,String age,String breedDate,String pregnancyDate, String dateCalved){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("Age",age);
        contentValues.put("Breed_date",breedDate);
        contentValues.put("Pregnancy_date",pregnancyDate);
        contentValues.put("Date_calved",dateCalved);
        long result = db.insert("cowBreeding",null,contentValues);
        return result != -1;
    }

    public boolean CheckRedundantData(String name,String age){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("select * from cowMilkProduction where Name = ? and Date = ?",new String[]{name,age});
        return cursor.getCount() > 0;
    }
}
