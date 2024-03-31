package daniel.brian.dairyfarmapp.admin.birds_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.dairyfarmapp.databinding.FragmentPoultryFinancesBinding;
import daniel.brian.dairyfarmapp.db.PoultryFinancesDB;

public class PoultryFinancesFragment extends Fragment {
   FragmentPoultryFinancesBinding poultryFinancesBinding;
   PoultryFinancesDB poultryFinancesDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        poultryFinancesBinding = FragmentPoultryFinancesBinding.inflate(getLayoutInflater());

        // Declaring the database
        poultryFinancesDB = new PoultryFinancesDB(this.getContext());

        // Saving the finances details
        poultryFinancesBinding.saveFinancesDetails.setOnClickListener(view -> {
            // Accessing the details
            String medic = Objects.requireNonNull(poultryFinancesBinding.medExpense.getText()).toString();
            String feed = Objects.requireNonNull(poultryFinancesBinding.feedExpense.getText()).toString();
            String labour = Objects.requireNonNull(poultryFinancesBinding.labourExpense.getText()).toString();
            String maintenance = Objects.requireNonNull(poultryFinancesBinding.maintenanceExpense.getText()).toString();
            String eggsSale = Objects.requireNonNull(poultryFinancesBinding.eggsIncome.getText()).toString();
            String meatSale = Objects.requireNonNull(poultryFinancesBinding.meatIncome.getText()).toString();
            String date = Objects.requireNonNull(poultryFinancesBinding.usageDate.getText()).toString();
            String purpose = Objects.requireNonNull(poultryFinancesBinding.purpose.getText()).toString();
            String amount = Objects.requireNonNull(poultryFinancesBinding.amountUsed.getText()).toString();
            String income = Objects.requireNonNull(poultryFinancesBinding.incomePaid.getText()).toString();

            if(
                    date.isEmpty() || purpose.isEmpty() || amount.isEmpty() || income.isEmpty() ||
                    medic.isEmpty() || feed.isEmpty() || labour.isEmpty() || maintenance.isEmpty() ||
                    eggsSale.isEmpty() || meatSale.isEmpty()
            ){
                Snackbar.make(requireView(),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                boolean cowFinancesRedundantData = poultryFinancesDB.CheckRedundantData(date,purpose);
                if(cowFinancesRedundantData){
                    Snackbar.make(requireView(),"Oops! Such Details Already exists!",Snackbar.LENGTH_LONG).show();
                }else{
                  boolean cowFinance = poultryFinancesDB.SaveCowFinancesRecords(medic,feed,labour,maintenance,eggsSale,meatSale,date,purpose,amount,income);
                  if(cowFinance){
                      Snackbar.make(requireView(),"Poultry Finances Report Saved Successfully!",Snackbar.LENGTH_LONG).show();
                  }else{
                      Snackbar.make(requireView(),"Oops! Such Details Already exists!",Snackbar.LENGTH_LONG).show();
                  }
                }
            }
        });
        return poultryFinancesBinding.getRoot();
    }
}