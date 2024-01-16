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
import daniel.brian.dairyfarmapp.databinding.FragmentCowBreedingBinding;
import daniel.brian.dairyfarmapp.db.CowBreedingDB;

public class CowBreedingFragment extends Fragment {
    FragmentCowBreedingBinding breedingBinding;
    CowBreedingDB cowBreedingDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        breedingBinding = FragmentCowBreedingBinding.inflate(getLayoutInflater());

        // Declaring the database
        cowBreedingDB = new CowBreedingDB(this.getContext());

        // Saving the meal details
        breedingBinding.saveBreedingDetails.setOnClickListener(view -> {
            // Accessing the details
            String name = Objects.requireNonNull(breedingBinding.cowName.getText()).toString();
            String age = Objects.requireNonNull(breedingBinding.cowAge.getText()).toString();
            String breedDate = Objects.requireNonNull(breedingBinding.breedDate.getText()).toString();
            String pregnancyDate = Objects.requireNonNull(breedingBinding.pregnancyDate.getText()).toString();
            String dateCalved = Objects.requireNonNull(breedingBinding.dateCalved.getText()).toString();

            if(name.isEmpty() || age.isEmpty() || breedDate.isEmpty() || pregnancyDate.isEmpty() || dateCalved.isEmpty()){
                Snackbar.make(requireView(),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                boolean cowBreeding = cowBreedingDB.SaveCowBreedingRecords(name,age,breedDate,pregnancyDate,dateCalved);
                if(cowBreeding){
                    Snackbar.make(requireView(),"Cow Breeding Details Saved Successfully!",Snackbar.LENGTH_LONG).show();
                } else{
                    Snackbar.make(requireView(),"Oops! Such Details Already Exists!",Snackbar.LENGTH_LONG).show();
                }
            }
        });
        return breedingBinding.getRoot();
    }
}