package daniel.brian.dairyfarmapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MilkSalesDB extends SQLiteOpenHelper {
    public MilkSalesDB(@Nullable Context context) {
        super(context, "MilkSale.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("Create Table milkSales(" +
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
        db.execSQL("drop Table if exists milkSales");
    }

    // function to save the milk sales
    public boolean SaveMilkSalesRecords(String date,String cName,String cPhone,String quantity,String price,String total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date",date);
        contentValues.put("Client_Name",cName);
        contentValues.put("Client_Phone",cPhone);
        contentValues.put("Quantity",quantity);
        contentValues.put("Price",price);
        contentValues.put("Total",total);
        long result = db.insert("milkSales",null,contentValues);
        return result != -1;
    }

    public boolean CheckRedundantData(String name,String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("select * from milkSales where Client_Name = ? and Client_Phone = ?",new String[]{name,phone});
        return cursor.getCount() > 0;
    }
}
