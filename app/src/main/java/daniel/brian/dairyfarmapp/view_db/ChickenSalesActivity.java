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
import daniel.brian.dairyfarmapp.adapters.ChickenSalesAdapter;
import daniel.brian.dairyfarmapp.adapters.EggsSalesAdapter;
import daniel.brian.dairyfarmapp.databinding.ActivityChickenSalesBinding;
import daniel.brian.dairyfarmapp.databinding.ActivityEggsSaleBinding;
import daniel.brian.dairyfarmapp.db.ChickenSalesDB;
import daniel.brian.dairyfarmapp.db.EggsSalesDB;

public class ChickenSalesActivity extends AppCompatActivity {
    ActivityChickenSalesBinding chickenSalesBinding;

    RecyclerView recyclerView;
    ChickenSalesDB chickenSalesDB;
    ChickenSalesAdapter chickenSalesAdapter;
    ArrayList<String> sellingDateC, clientNameC, clientPhoneC, chickenQuantity, chickenPrice, totalAmountC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chickenSalesBinding = ActivityChickenSalesBinding.inflate(getLayoutInflater());
        setContentView(chickenSalesBinding.getRoot());

        chickenSalesDB = new ChickenSalesDB(this);
        sellingDateC = new ArrayList<>();
        clientNameC = new ArrayList<>();
        clientPhoneC = new ArrayList<>();
        chickenQuantity = new ArrayList<>();
        chickenPrice = new ArrayList<>();
        totalAmountC = new ArrayList<>();

        recyclerView = chickenSalesBinding.chickenSalesRV;
        chickenSalesAdapter = new ChickenSalesAdapter(this,sellingDateC,clientNameC,clientPhoneC,chickenQuantity,chickenPrice,totalAmountC);
        recyclerView.setAdapter(chickenSalesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        displayData();

    }

    private void displayData() {
        Cursor cursor = chickenSalesDB.getChickenSales();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Items Available", Toast.LENGTH_LONG).show();
        } else {

            while (cursor.moveToNext()) {
                sellingDateC.add(cursor.getString(0));
                clientNameC.add(cursor.getString(1));
                clientPhoneC.add(cursor.getString(2));
                chickenQuantity.add(cursor.getString(3));
                chickenPrice.add(cursor.getString(4));
                totalAmountC.add(cursor.getString(5));
            }

            // Move the adapter update here to make sure it gets updated with the new data
            chickenSalesAdapter.notifyDataSetChanged();
        }
    }
}