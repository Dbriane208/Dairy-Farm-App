package daniel.brian.dairyfarmapp.view_db;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.R;
import daniel.brian.dairyfarmapp.adapters.PoultryDetailsAdapter;
import daniel.brian.dairyfarmapp.adapters.PoultryProductionAdapter;
import daniel.brian.dairyfarmapp.databinding.ActivityPoultryProductionBinding;
import daniel.brian.dairyfarmapp.db.PoultryDetailsDB;
import daniel.brian.dairyfarmapp.db.PoultryProductionDB;

public class PoultryProductionActivity extends AppCompatActivity {
    ActivityPoultryProductionBinding poultryProductionBinding;
    RecyclerView recyclerView;
    PoultryProductionDB poultryProductionDB;
    PoultryProductionAdapter poultryProductionAdapter;
    ArrayList<String> batchName, meatProduction, servedBy, timeProduced, collectionArea, collectionDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        poultryProductionBinding = ActivityPoultryProductionBinding.inflate(getLayoutInflater());
        setContentView(poultryProductionBinding.getRoot());

        poultryProductionDB = new PoultryProductionDB(this);
        batchName = new ArrayList<>();
        meatProduction = new ArrayList<>();
        servedBy = new ArrayList<>();
        timeProduced = new ArrayList<>();
        collectionArea = new ArrayList<>();
        collectionDate = new ArrayList<>();

        recyclerView = poultryProductionBinding.poultryProductionRV;
        poultryProductionAdapter = new PoultryProductionAdapter(this,batchName, meatProduction, servedBy, timeProduced, collectionArea, collectionDate);
        recyclerView.setAdapter(poultryProductionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        displayData();
    }

    private void displayData() {
        Cursor cursor = poultryProductionDB.getPoultryProduction();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Items Available", Toast.LENGTH_LONG).show();
        } else {

            while (cursor.moveToNext()) {
                batchName.add(cursor.getString(0));
                meatProduction.add(cursor.getString(1));
                servedBy.add(cursor.getString(2));
                timeProduced.add(cursor.getString(3));
                collectionArea.add(cursor.getString(4));
                collectionDate.add(cursor.getString(5));
            }

            // Move the adapter update here to make sure it gets updated with the new data
            poultryProductionAdapter.notifyDataSetChanged();
        }
    }
}