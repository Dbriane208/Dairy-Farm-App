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
import daniel.brian.dairyfarmapp.adapters.EggsSalesAdapter;
import daniel.brian.dairyfarmapp.adapters.PoultryDetailsAdapter;
import daniel.brian.dairyfarmapp.databinding.ActivityEggsSaleBinding;
import daniel.brian.dairyfarmapp.db.EggsSalesDB;
import daniel.brian.dairyfarmapp.db.PoultryDetailsDB;

public class EggsSaleActivity extends AppCompatActivity {
    ActivityEggsSaleBinding eggsSaleBinding;

    RecyclerView recyclerView;
    EggsSalesDB eggsSalesDB;
    EggsSalesAdapter eggsSalesAdapter;
    ArrayList<String> sellingDate,clientName,clientPhone,eggsQuantity,eggsPrice,totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eggsSaleBinding = ActivityEggsSaleBinding.inflate(getLayoutInflater());
        setContentView(eggsSaleBinding.getRoot());

        eggsSalesDB = new EggsSalesDB(this);
        sellingDate = new ArrayList<>();
        clientName = new ArrayList<>();
        clientPhone = new ArrayList<>();
        eggsQuantity = new ArrayList<>();
        eggsPrice = new ArrayList<>();
        totalAmount = new ArrayList<>();

        recyclerView = eggsSaleBinding.eggsSalesRV;
        eggsSalesAdapter = new EggsSalesAdapter(this,sellingDate,clientName,clientPhone,eggsQuantity,eggsPrice,totalAmount);
        recyclerView.setAdapter(eggsSalesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        displayData();
    }

    private void displayData() {
        Cursor cursor = eggsSalesDB.getEggSales();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Items Available", Toast.LENGTH_LONG).show();
        } else {

            while (cursor.moveToNext()) {
                sellingDate.add(cursor.getString(0));
                clientName.add(cursor.getString(1));
                clientPhone.add(cursor.getString(2));
                eggsQuantity.add(cursor.getString(3));
                eggsPrice.add(cursor.getString(4));
                totalAmount.add(cursor.getString(5));
            }

            // Move the adapter update here to make sure it gets updated with the new data
            eggsSalesAdapter.notifyDataSetChanged();
        }
    }
}