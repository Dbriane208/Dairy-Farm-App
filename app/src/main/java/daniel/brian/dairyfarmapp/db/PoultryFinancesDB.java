package daniel.brian.dairyfarmapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PoultryFinancesDB extends SQLiteOpenHelper {
    public PoultryFinancesDB(@Nullable Context context) {
        super(context, "CowsFinances.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("Create Table cowFinances(" +
                 "Finance_id integer primary key not null," +
                 "Date DATE not null," +
                 "Purpose varchar(50) not null," +
                 "Amount Text not null," +
                 "Income Text not null," +
                 "constraint cowFinance unique(Finance_id,Date,Purpose,Amount))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
         db.execSQL("drop Table if exists cowFinances");
    }

    // function to save the Finances
    public boolean SaveCowFinancesRecords(String date, String purpose, String amount, String income){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date",date);
        contentValues.put("Purpose",purpose);
        contentValues.put("Amount",amount);
        contentValues.put("Income",income);
       long result =  db.insert("cowFinances",null,contentValues);
       return result != -1;
    }

    public boolean CheckRedundantData(String date,String purpose){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("select * from cowFinances where Date = ? and Purpose = ?",new String[]{date,purpose});
        return cursor.getCount() > 0;
    }
}
