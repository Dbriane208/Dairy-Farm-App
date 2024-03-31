package daniel.brian.dairyfarmapp.admin.birds_details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.dairyfarmapp.databinding.FragmentPoultryFeedingBinding;
import daniel.brian.dairyfarmapp.db.PoultryFeedingDB;

public class PoultryFeedingFragment extends Fragment {
    FragmentPoultryFeedingBinding poultryFeedingBinding;
    PoultryFeedingDB poultryFeedingDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        poultryFeedingBinding =  FragmentPoultryFeedingBinding.inflate(getLayoutInflater());

        // Declaring the database
        poultryFeedingDB = new PoultryFeedingDB(this.getContext());

        // Saving the meal details
        poultryFeedingBinding.saveFeedingDetails.setOnClickListener(view -> {
            // Accessing the details
            String category = Objects.requireNonNull(poultryFeedingBinding.categoryFed.getText()).toString();
            String type = Objects.requireNonNull(poultryFeedingBinding.feedType.getText()).toString();
            String typeAm = Objects.requireNonNull(poultryFeedingBinding.feedAm.getText()).toString();
            String typeNoon = Objects.requireNonNull(poultryFeedingBinding.feedNoon.getText()).toString();
            String typePm = Objects.requireNonNull(poultryFeedingBinding.feedPM.getText()).toString();
            String feedingPerson = Objects.requireNonNull(poultryFeedingBinding.feedPerson.getText()).toString();
            String quantity = Objects.requireNonNull(poultryFeedingBinding.quantityFed.getText()).toString();
            String cost = Objects.requireNonNull(poultryFeedingBinding.feedCost.getText()).toString();
            String date = Objects.requireNonNull(poultryFeedingBinding.datePurchased.getText()).toString();

            if(category.isEmpty() || type.isEmpty() || typeAm.isEmpty() || typeNoon.isEmpty() || typePm.isEmpty() || feedingPerson.isEmpty() || quantity.isEmpty() || cost.isEmpty() || date.isEmpty()){
                Snackbar.make(requireView(),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                boolean cowBreedingRedundantData = poultryFeedingDB.CheckRedundantData(category,date);
                if(cowBreedingRedundantData){
                    Snackbar.make(requireView(),"Oops! Such Details Already Exists!",Snackbar.LENGTH_LONG).show();
                }else{
                    boolean cowBreeding = poultryFeedingDB.SavePoultryFeedingRecords(category,type,typeAm,typeNoon,typePm,feedingPerson,quantity,cost,date);
                    if(cowBreeding){
                        Snackbar.make(requireView(),"Poultry Feeding Details Saved Successfully!",Snackbar.LENGTH_LONG).show();
                    }else{
                        Snackbar.make(requireView(),"Oops! Such Details Already Exists!",Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
        return poultryFeedingBinding.getRoot();
    }
}