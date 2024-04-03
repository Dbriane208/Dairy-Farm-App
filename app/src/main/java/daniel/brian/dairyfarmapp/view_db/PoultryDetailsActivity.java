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
import daniel.brian.dairyfarmapp.databinding.ActivityPoultryDetailsBinding;
import daniel.brian.dairyfarmapp.db.PoultryDetailsDB;
import daniel.brian.dairyfarmapp.db.PoultryProductionDB;

public class PoultryDetailsActivity extends AppCompatActivity {
    ActivityPoultryDetailsBinding activityPoultryDetailsBinding;
    RecyclerView recyclerView;
    PoultryDetailsDB poultryDetailsDB;
    PoultryDetailsAdapter poultryDetailsAdapter;
    ArrayList<String> layersBirds,broilersBirds,totalBirds,acquisitionDate,acquisitionType,acquisitionCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPoultryDetailsBinding = ActivityPoultryDetailsBinding.inflate(getLayoutInflater());
        setContentView(activityPoultryDetailsBinding.getRoot());

        poultryDetailsDB = new PoultryDetailsDB(this);
        layersBirds = new ArrayList<>();
        broilersBirds = new ArrayList<>();
        totalBirds = new ArrayList<>();
        acquisitionDate = new ArrayList<>();
        acquisitionType = new ArrayList<>();
        acquisitionCost = new ArrayList<>();
        
        recyclerView = activityPoultryDetailsBinding.poultryDetailsRV;
        poultryDetailsAdapter = new PoultryDetailsAdapter(this,layersBirds,broilersBirds,totalBirds,acquisitionDate,acquisitionType,acquisitionCost);
        recyclerView.setAdapter(poultryDetailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        displayData();
         
    }

    private void displayData() {
        Cursor cursor = poultryDetailsDB.getPoultryDetails();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Items Available", Toast.LENGTH_LONG).show();
        } else {

            while (cursor.moveToNext()) {
                layersBirds.add(cursor.getString(0));
                broilersBirds.add(cursor.getString(1));
                totalBirds.add(cursor.getString(2));
                acquisitionDate.add(cursor.getString(3));
                acquisitionType.add(cursor.getString(4));
                acquisitionCost.add(cursor.getString(5));
            }

            // Move the adapter update here to make sure it gets updated with the new data
            poultryDetailsAdapter.notifyDataSetChanged();
        }
    }
}