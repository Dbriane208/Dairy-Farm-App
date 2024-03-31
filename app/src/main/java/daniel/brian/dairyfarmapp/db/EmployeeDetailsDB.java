package daniel.brian.dairyfarmapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EmployeeDetailsDB extends SQLiteOpenHelper {
    public EmployeeDetailsDB (@Nullable Context context) {
        super(context, "EmployeeDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table EmployeeDetails(" +
                "ArrivalTime Text not null," +
                "EmployeeId Text not null," +
                "ActivitiesDone Text not null,"+
                "Gender Text not null,"+
                "EmployeeName varchar(10) not null,"+
                "EmployeeRole varchar(10) not null,"+
                "DepartureTime varchar(10) not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists EmployeeDetails");
    }

    // function to save breeding records
    public boolean SaveEmployeeDetails(String arrival, String id, String activitiesDone, String gender, String name, String role, String departure){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ArrivalTime",arrival);
        contentValues.put("EmployeeId",id);
        contentValues.put("ActivitiesDone",activitiesDone);
        contentValues.put("Gender",gender);
        contentValues.put("EmployeeName",name);
        contentValues.put("EmployeeRole",role);
        contentValues.put("DepartureTime",departure);
        long result = db.insert("EmployeeDetails",null,contentValues);
        return result != -1;
    }

    public boolean CheckRedundantData(String employeeId,String activity){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("select * from EmployeeDetails where EmployeeId = ? and ActivitiesDone = ?",new String[]{employeeId,activity});
        return cursor.getCount() > 0;
    }

}
