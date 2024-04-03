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
import daniel.brian.dairyfarmapp.adapters.EmployeeDetailsAdapter;
import daniel.brian.dairyfarmapp.adapters.PoultryDetailsAdapter;
import daniel.brian.dairyfarmapp.databinding.ActivityEmployeeDetailsBinding;
import daniel.brian.dairyfarmapp.db.EmployeeDetailsDB;
import daniel.brian.dairyfarmapp.db.PoultryDetailsDB;

public class EmployeeDetailsActivity extends AppCompatActivity {
    ActivityEmployeeDetailsBinding employeeDetailsBinding;

    RecyclerView recyclerView;
    EmployeeDetailsDB employeeDetailsDB;
    EmployeeDetailsAdapter employeeDetailsAdapter;
    ArrayList<String> arrivalTime, employeeId, gender, employeeName, employeeRole, departureTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employeeDetailsBinding = ActivityEmployeeDetailsBinding.inflate(getLayoutInflater());
        setContentView(employeeDetailsBinding.getRoot());

        employeeDetailsDB = new EmployeeDetailsDB(this);
        arrivalTime = new ArrayList<>();
        employeeId = new ArrayList<>();
        gender = new ArrayList<>();
        employeeName = new ArrayList<>();
        employeeRole = new ArrayList<>();
        departureTime = new ArrayList<>();

        recyclerView = employeeDetailsBinding.employeeDetailsRV;
        employeeDetailsAdapter = new EmployeeDetailsAdapter(this,arrivalTime, employeeId, gender, employeeName, employeeRole, departureTime);
        recyclerView.setAdapter(employeeDetailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        displayData();
    }

    private void displayData() {
        Cursor cursor = employeeDetailsDB.getEmployeeDetails();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Items Available", Toast.LENGTH_LONG).show();
        } else {

            while (cursor.moveToNext()) {
                arrivalTime.add(cursor.getString(0));
                employeeId.add(cursor.getString(1));
                gender.add(cursor.getString(2));
                employeeName.add(cursor.getString(3));
                employeeRole.add(cursor.getString(4));
                departureTime.add(cursor.getString(5));
            }

            // Move the adapter update here to make sure it gets updated with the new data
            employeeDetailsAdapter.notifyDataSetChanged();
        }
    }
}