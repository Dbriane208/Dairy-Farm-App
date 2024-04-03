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
import daniel.brian.dairyfarmapp.adapters.FinancesReportAdapter;
import daniel.brian.dairyfarmapp.adapters.PoultryFeedingAdapter;
import daniel.brian.dairyfarmapp.databinding.ActivityFinancesReportBinding;
import daniel.brian.dairyfarmapp.db.PoultryFeedingDB;
import daniel.brian.dairyfarmapp.db.PoultryFinancesDB;

public class FinancesReportActivity extends AppCompatActivity {
    ActivityFinancesReportBinding financesReportBinding;
    RecyclerView recyclerView;
    PoultryFinancesDB poultryFinancesDB;
    FinancesReportAdapter financesReportAdapter;
    ArrayList<String> medicalExpenses, feedExpenses, labourExpenses, expenditure, totalExpenses, totalIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        financesReportBinding = ActivityFinancesReportBinding.inflate(getLayoutInflater());
        setContentView(financesReportBinding.getRoot());

        poultryFinancesDB = new PoultryFinancesDB(this);
        medicalExpenses = new ArrayList<>();
        feedExpenses = new ArrayList<>();
        labourExpenses = new ArrayList<>();
        expenditure = new ArrayList<>();
        totalExpenses = new ArrayList<>();
        totalIncome = new ArrayList<>();

        recyclerView = financesReportBinding.financesReportRV;
        financesReportAdapter = new FinancesReportAdapter(this,medicalExpenses, feedExpenses, labourExpenses, expenditure, totalExpenses, totalIncome);
        recyclerView.setAdapter(financesReportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        displayData();
    }

    private void displayData() {
        Cursor cursor = poultryFinancesDB.getFinancesReports();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Items Available", Toast.LENGTH_LONG).show();
        } else {

            while (cursor.moveToNext()) {
                medicalExpenses.add(cursor.getString(0));
                feedExpenses.add(cursor.getString(1));
                labourExpenses.add(cursor.getString(2));
                expenditure.add(cursor.getString(3));
                totalExpenses.add(cursor.getString(4));
                totalIncome.add(cursor.getString(5));
            }

            // Move the adapter update here to make sure it gets updated with the new data
            financesReportAdapter.notifyDataSetChanged();
        }
    }
}