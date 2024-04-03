package daniel.brian.dairyfarmapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PoultryDetailsDB extends SQLiteOpenHelper {
    public PoultryDetailsDB(@Nullable Context context) {
        super(context, "PoultryDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table PoultryDetails(" +
                "Layers varchar(10) not null," +
                "Broilers varchar(10) primary key unique not null," +
                "Total_Birds varchar(10) not null," +
                "Date_of_Acquisition DATE not null," +
                "Type_of_Acquisition varchar(10) not null," +
                "Cost_of_Acquisition Text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("drop Table if exists PoultryDetails");
    }

    // Register a new cow
    public boolean PoultryRegistration(String layers, String broilers, String total_Birds, String doa, String toa, String coa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Layers",layers);
        contentValues.put("Broilers",broilers);
        contentValues.put("Total_Birds",total_Birds);
        contentValues.put("Date_of_Acquisition",doa);
        contentValues.put("Type_of_Acquisition",toa);
        contentValues.put("Cost_of_Acquisition",coa);

        long result = db.insert("PoultryDetails",null,contentValues);
        return result != -1;
    }

    public Cursor getPoultryDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from PoultryDetails ",null);
    }
}
