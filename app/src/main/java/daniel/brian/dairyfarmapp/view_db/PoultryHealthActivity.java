package daniel.brian.dairyfarmapp.view_db;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.adapters.PoultryHealthAdapter;
import daniel.brian.dairyfarmapp.databinding.ActivityPoultryHealthBinding;
import daniel.brian.dairyfarmapp.db.PoultryHealthDB;

public class PoultryHealthActivity extends AppCompatActivity {
    ActivityPoultryHealthBinding poultryHealthBinding;
    RecyclerView recyclerView;
    PoultryHealthDB poultryHealthDB;
    PoultryHealthAdapter poultryHealthAdapter;
    ArrayList<String> batchNameHealth, categoryAffected, vaccination, diseaseName, vaccineName, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        poultryHealthBinding = ActivityPoultryHealthBinding.inflate(getLayoutInflater());
        setContentView(poultryHealthBinding.getRoot());

        poultryHealthDB = new PoultryHealthDB(this);
        batchNameHealth = new ArrayList<>();
        categoryAffected = new ArrayList<>();
        vaccination = new ArrayList<>();
        diseaseName = new ArrayList<>();
        vaccineName = new ArrayList<>();
        date = new ArrayList<>();

        recyclerView = poultryHealthBinding.poultryHealthRV;
        poultryHealthAdapter = new PoultryHealthAdapter(this,batchNameHealth, categoryAffected, vaccination, diseaseName, vaccineName, date);
        recyclerView.setAdapter(poultryHealthAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        displayData();
    }

    private void displayData() {
        Cursor cursor = poultryHealthDB.getPoultryHealth();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Items Available", Toast.LENGTH_LONG).show();
        } else {

            while (cursor.moveToNext()) {
                batchNameHealth.add(cursor.getString(0));
                categoryAffected.add(cursor.getString(1));
                vaccination.add(cursor.getString(2));
                diseaseName.add(cursor.getString(3));
                vaccineName.add(cursor.getString(4));
                date.add(cursor.getString(5));
            }

            // Move the adapter update here to make sure it gets updated with the new data
            poultryHealthAdapter.notifyDataSetChanged();
        }
    }
}