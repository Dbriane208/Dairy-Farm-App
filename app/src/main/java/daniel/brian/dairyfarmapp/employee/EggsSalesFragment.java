package daniel.brian.dairyfarmapp.employee;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.dairyfarmapp.databinding.FragmentEggsSalesBinding;
import daniel.brian.dairyfarmapp.db.EggsSalesDB;

public class EggsSalesFragment extends Fragment {
    FragmentEggsSalesBinding eggsSalesBinding;

    EggsSalesDB eggsSalesDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        eggsSalesBinding = FragmentEggsSalesBinding.inflate(getLayoutInflater());

        // Declaring the database
        eggsSalesDB = new EggsSalesDB(this.getContext());

        // Saving the meals details
        eggsSalesBinding.saveEggsSales.setOnClickListener(view -> {
            // Accessing the details
            String date = Objects.requireNonNull(eggsSalesBinding.eggsSellingDate.getText()).toString();
            String clientName = Objects.requireNonNull(eggsSalesBinding.eggsClientName.getText()).toString();
            String clientPhone = Objects.requireNonNull(eggsSalesBinding.eggsClientPhone.getText()).toString();
            String quantity = Objects.requireNonNull(eggsSalesBinding.eggsQuantity.getText()).toString();
            String price = Objects.requireNonNull(eggsSalesBinding.eggsPrice.getText()).toString();
            String total = Objects.requireNonNull(eggsSalesBinding.eggsTotal.getText()).toString();

            if(date.isEmpty() || clientName.isEmpty() || clientPhone.length() != 10 || quantity.isEmpty() || total.isEmpty() || price.isEmpty()){
                Snackbar.make(requireView(),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                boolean sales = eggsSalesDB.CheckRedundantData(clientName,clientPhone);
                if(sales){
                    Snackbar.make(requireView(),"Oops! Such Details Already exists!",Snackbar.LENGTH_LONG).show();
                }else{
                    boolean milkDetails = eggsSalesDB.SaveEggsSalesRecords(date,clientName,clientPhone,quantity,price,total);
                    if(milkDetails){
                        Snackbar.make(requireView(),"Eggs Sales Details Saved Successfully!",Snackbar.LENGTH_LONG).show();
                    }else{
                        Snackbar.make(requireView(),"Oops! Such Details Already exists!",Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
        return eggsSalesBinding.getRoot();
    }
}