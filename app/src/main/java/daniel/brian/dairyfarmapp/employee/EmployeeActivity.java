package daniel.brian.dairyfarmapp.employee;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.dairyfarmapp.databinding.ActivityEmployeeBinding;
import daniel.brian.dairyfarmapp.db.MilkSalesDB;

public class EmployeeActivity extends AppCompatActivity {
    ActivityEmployeeBinding employeeBinding;
    MilkSalesDB milkSalesDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employeeBinding = ActivityEmployeeBinding.inflate(getLayoutInflater());
        setContentView(employeeBinding.getRoot());

        // Declaring the database
        milkSalesDB = new MilkSalesDB(this);

        // Saving the meals details
        employeeBinding.saveMilkSales.setOnClickListener(view -> {
            // Accessing the details
            String date = Objects.requireNonNull(employeeBinding.sellingDate.getText()).toString();
            String clientName = Objects.requireNonNull(employeeBinding.clientName.getText()).toString();
            String clientPhone = Objects.requireNonNull(employeeBinding.clientPhone.getText()).toString();
            String quantity = Objects.requireNonNull(employeeBinding.quantity.getText()).toString();
            String price = Objects.requireNonNull(employeeBinding.price.getText()).toString();
            String total = Objects.requireNonNull(employeeBinding.total.getText()).toString();

            if(date.isEmpty() || clientName.isEmpty() || clientPhone.length() != 10 || quantity.isEmpty() || total.isEmpty() || price.isEmpty()){
                Snackbar.make(Objects.requireNonNull(this.getCurrentFocus()),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                boolean milkDetails = milkSalesDB.SaveMilkSalesRecords(date,clientName,clientPhone,quantity,price,total);
                if(milkDetails){
                    Snackbar.make(Objects.requireNonNull(this.getCurrentFocus()),"Milk Sales Details Saved Successfully!",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(Objects.requireNonNull(this.getCurrentFocus()),"Oops! Such Details Already exists!",Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}