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
import daniel.brian.dairyfarmapp.databinding.FragmentMilkProductionBinding;
import daniel.brian.dairyfarmapp.db.CowMilkProductionDB;

public class MilkProductionFragment extends Fragment {
   FragmentMilkProductionBinding milkProductionBinding;
   CowMilkProductionDB cowMilkProductionDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        milkProductionBinding = FragmentMilkProductionBinding.inflate(getLayoutInflater());

        // Initializing the database
        cowMilkProductionDB = new CowMilkProductionDB(this.getContext());

        // saving the milk production details
        milkProductionBinding.saveMilkProdDetails.setOnClickListener(view -> {
            //access the details
            String cowName = Objects.requireNonNull(milkProductionBinding.cowName.getText()).toString();
            String amMilk = Objects.requireNonNull(milkProductionBinding.amMilk.getText()).toString();
            String noonMilk = Objects.requireNonNull(milkProductionBinding.noonMilk.getText()).toString();
            String pmMilk = Objects.requireNonNull(milkProductionBinding.pmMilk.getText()).toString();
            String totalMilk = Objects.requireNonNull(milkProductionBinding.totalMilk.getText()).toString();
            String date = Objects.requireNonNull(milkProductionBinding.milkProdDate.getText()).toString();

            if(cowName.isEmpty() || amMilk.isEmpty() || noonMilk.isEmpty() || pmMilk.isEmpty() || totalMilk.isEmpty() || date.isEmpty()){
                Snackbar.make(requireView(),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                boolean checkMilkRedundantData = cowMilkProductionDB.CheckRedundantData(cowName,date);
                if(checkMilkRedundantData){
                    Snackbar.make(requireView(),"Oops! Such Details Already Exists!",Snackbar.LENGTH_LONG).show();
                }else{
                    boolean milkProduction = cowMilkProductionDB.saveMilkProductionDetails(cowName,amMilk,noonMilk,pmMilk,totalMilk,date);
                    if(milkProduction){
                        Snackbar.make(requireView(),"Milk Produce Details Saved Successfully!",Snackbar.LENGTH_LONG).show();
                    }else{
                        Snackbar.make(requireView(),"Oops! Such Details Already Exists!",Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
        return milkProductionBinding.getRoot();
    }
}