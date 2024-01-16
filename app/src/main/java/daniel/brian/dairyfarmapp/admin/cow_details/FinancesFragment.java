package daniel.brian.dairyfarmapp.admin.cow_details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.dairyfarmapp.R;
import daniel.brian.dairyfarmapp.databinding.FragmentFinancesBinding;
import daniel.brian.dairyfarmapp.db.CowFinancesDB;

public class FinancesFragment extends Fragment {
FragmentFinancesBinding financesBinding;
CowFinancesDB cowFinancesDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        financesBinding = FragmentFinancesBinding.inflate(getLayoutInflater());

        // Declaring the database
        cowFinancesDB = new CowFinancesDB(this.getContext());

        // Saving the finances details
        financesBinding.saveFinancesDetails.setOnClickListener(view -> {
            // Accessing the details
            String date = Objects.requireNonNull(financesBinding.usageDate.getText()).toString();
            String purpose = Objects.requireNonNull(financesBinding.purpose.getText()).toString();
            String amount = Objects.requireNonNull(financesBinding.amountUsed.getText()).toString();

            if(date.isEmpty() || purpose.isEmpty() || amount.isEmpty()){
                Snackbar.make(requireView(),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                boolean cowFinances = cowFinancesDB.SaveCowFinancesRecords(date,purpose,amount);
                if(cowFinances){
                    Snackbar.make(requireView(),"Cow Finances Saved Successfully!",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(requireView(),"Oops! Such Details Already exists!",Snackbar.LENGTH_LONG).show();
                }
            }
        });
        return financesBinding.getRoot();
    }
}