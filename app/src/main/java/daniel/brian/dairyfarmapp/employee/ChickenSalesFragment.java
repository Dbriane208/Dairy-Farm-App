package daniel.brian.dairyfarmapp.employee;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.dairyfarmapp.databinding.FragmentChickenSalesBinding;
import daniel.brian.dairyfarmapp.db.ChickenSalesDB;

public class ChickenSalesFragment extends Fragment {
    FragmentChickenSalesBinding chickenSalesBinding;
    ChickenSalesDB chickenSalesDB;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        chickenSalesBinding = FragmentChickenSalesBinding.inflate(getLayoutInflater());

        // Declaring the database
        chickenSalesDB = new ChickenSalesDB(this.getContext());

        // Saving the chicken sales details
        chickenSalesBinding.saveChickenSales.setOnClickListener(view -> {
            // Accessing the details
            String date = Objects.requireNonNull(chickenSalesBinding.chickenSellingDate.getText()).toString();
            String clientName = Objects.requireNonNull(chickenSalesBinding.chickenClientName.getText()).toString();
            String clientPhone = Objects.requireNonNull(chickenSalesBinding.chickenClientPhone.getText()).toString();
            String quantity = Objects.requireNonNull(chickenSalesBinding.chickenSize.getText()).toString();
            String price = Objects.requireNonNull(chickenSalesBinding.chickenPrice.getText()).toString();
            String total = Objects.requireNonNull(chickenSalesBinding.chickenTotal.getText()).toString();

            if(date.isEmpty() || clientName.isEmpty() || clientPhone.length() != 10 || quantity.isEmpty() || total.isEmpty() || price.isEmpty()){
                Snackbar.make(requireView(),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                boolean sales = chickenSalesDB.CheckRedundantData(clientName,clientPhone);
                if(sales){
                    Snackbar.make(requireView(),"Oops! Such Details Already exists!",Snackbar.LENGTH_LONG).show();
                }else{
                    boolean milkDetails = chickenSalesDB.SaveChickenSalesRecords(date,clientName,clientPhone,quantity,price,total);
                    if(milkDetails){
                        Snackbar.make(requireView(),"Chicken Sales Details Saved Successfully!",Snackbar.LENGTH_LONG).show();
                    }else{
                        Snackbar.make(requireView(),"Oops! Such Details Already exists!",Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        return chickenSalesBinding.getRoot();
    }
}