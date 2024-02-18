package daniel.brian.dairyfarmapp.admin.birds_details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;


import daniel.brian.dairyfarmapp.databinding.FragmentPoultryProductionBinding;
import daniel.brian.dairyfarmapp.db.PoultryProductionDB;

public class PoultryProductionFragment extends Fragment {
   FragmentPoultryProductionBinding poultryProductionBinding;
   PoultryProductionDB poultryProductionDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        poultryProductionBinding =  FragmentPoultryProductionBinding.inflate(getLayoutInflater());

        // Initializing the database
        poultryProductionDB = new PoultryProductionDB(this.getContext());

        // saving the milk production details
        poultryProductionBinding.savePoultryProdDetails.setOnClickListener(view -> {
            //access the details
            String type = Objects.requireNonNull(poultryProductionBinding.collectionType.getText()).toString();
            String date = Objects.requireNonNull(poultryProductionBinding.collectionDate.getText()).toString();
            String eggsCollected = Objects.requireNonNull(poultryProductionBinding.collectedEggs.getText()).toString();
            String goodE = Objects.requireNonNull(poultryProductionBinding.goodEggs.getText()).toString();
            String brokenE = Objects.requireNonNull(poultryProductionBinding.brokenEggs.getText()).toString();
            String trays = Objects.requireNonNull(poultryProductionBinding.traysCollected.getText()).toString();

            if(type.isEmpty() || date.isEmpty() || eggsCollected.isEmpty() || goodE.isEmpty() || brokenE.isEmpty() || trays.isEmpty()){
                Snackbar.make(requireView(),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                boolean checkMilkRedundantData = poultryProductionDB.CheckRedundantData(type,date);
                if(checkMilkRedundantData){
                    Snackbar.make(requireView(),"Oops! Such Details Already Exists!",Snackbar.LENGTH_LONG).show();
                }else{
                    boolean milkProduction = poultryProductionDB.savePoultryProductionDetails(type,date,eggsCollected,goodE,brokenE,trays);
                    if(milkProduction){
                        Snackbar.make(requireView(),"Poultry Produce Details Saved Successfully!",Snackbar.LENGTH_LONG).show();
                    }else{
                        Snackbar.make(requireView(),"Oops! Such Details Already Exists!",Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
        return poultryProductionBinding.getRoot();
    }
}