package daniel.brian.dairyfarmapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CowDetailsDB extends SQLiteOpenHelper {
    public CowDetailsDB(@Nullable Context context) {
        super(context, "cowsDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table cowDetails(" +
                "Name varchar(10) not null," +
                "Ear_tag varchar(10) primary key unique not null," +
                "Color varchar(10) not null," +
                "Breed varchar(10) not null," +
                "Date_of_birth DATE not null," +
                "Age Text not null," +
                "Weight_at_birth Text not null," +
                "constraint cow unique (Name,Ear_tag))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("drop Table if exists cowDetails");
    }

    // Register a new cow
    public boolean CowRegistration(String Name,String Ear_tag,String Color,String Breed,String cowDob,String age,String cowWat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("Ear_tag",Ear_tag);
        contentValues.put("Color",Color);
        contentValues.put("Breed",Breed);
        contentValues.put("Date_of_birth",cowDob);
        contentValues.put("Age",age);
        contentValues.put("Weight_at_birth",cowWat);

        long result = db.insert("cowDetails",null,contentValues);
        return result != -1;
    }
}
