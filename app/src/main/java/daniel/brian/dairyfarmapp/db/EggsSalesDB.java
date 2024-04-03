package daniel.brian.dairyfarmapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EggsSalesDB extends SQLiteOpenHelper {
    public EggsSalesDB(@Nullable Context context) {
        super(context, "EggsSales.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("Create Table eggsSales(" +
               "Employee_id integer primary key not null," +
               "Date DATE not null," +
               "Client_Name varchar(10) not null," +
               "Client_Phone Text not null," +
               "Quantity Text not null," +
               "Price Text not null," +
               "Total Text not null," +
               "constraint sales unique(Employee_id,Client_Name,Client_Phone))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists eggsSales");
    }

    // function to save the eggs sales
    public boolean SaveEggsSalesRecords(String date, String cName, String cPhone, String quantity, String price, String total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date",date);
        contentValues.put("Client_Name",cName);
        contentValues.put("Client_Phone",cPhone);
        contentValues.put("Quantity",quantity);
        contentValues.put("Price",price);
        contentValues.put("Total",total);
        long result = db.insert("eggsSales",null,contentValues);
        return result != -1;
    }

    public boolean CheckRedundantData(String name,String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("select * from eggsSales where Client_Name = ? and Client_Phone = ?",new String[]{name,phone});
        return cursor.getCount() > 0;
    }

    public Cursor getEggSales(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from eggsSales ",null);
    }
}
