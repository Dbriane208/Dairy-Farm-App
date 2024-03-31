package daniel.brian.dairyfarmapp.admin.birds_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.dairyfarmapp.databinding.FragmentPoultryHealthBinding;
import daniel.brian.dairyfarmapp.db.PoultryHealthDB;

public class PoultryHealthFragment extends Fragment {
    FragmentPoultryHealthBinding poultryHealthBinding;
    PoultryHealthDB poultryHealthDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        poultryHealthBinding = FragmentPoultryHealthBinding.inflate(getLayoutInflater());

        // Declaring the poultryHealthDB
        poultryHealthDB = new PoultryHealthDB(this.getContext());

        // saving the health record
        poultryHealthBinding.saveHealthDetails.setOnClickListener(view -> {
            // accessing the details
            String batch = Objects.requireNonNull(poultryHealthBinding.batch.getText()).toString();
            String area = Objects.requireNonNull(poultryHealthBinding.collectionArea.getText()).toString();
            String medication = Objects.requireNonNull(poultryHealthBinding.vacOrMed.getText()).toString();
            String disease = Objects.requireNonNull(poultryHealthBinding.disease.getText()).toString();
            String vaccine = Objects.requireNonNull(poultryHealthBinding.vaccineName.getText()).toString();
            String date = Objects.requireNonNull(poultryHealthBinding.healthDate.getText()).toString();
            String affectedBirds = Objects.requireNonNull(poultryHealthBinding.birdsAffected.getText()).toString();
            String costOfTreatment = Objects.requireNonNull(poultryHealthBinding.treatmentCost.getText()).toString();
            String vetName = Objects.requireNonNull(poultryHealthBinding.vetName.getText()).toString();

            if(batch.isEmpty() || area.isEmpty() || medication.isEmpty() || disease.isEmpty() || vaccine.isEmpty() || date.isEmpty() || affectedBirds.isEmpty() || costOfTreatment.isEmpty() || vetName.isEmpty()){
                Snackbar.make(requireView(),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else {
                boolean cowHealthRedundantData = poultryHealthDB.CheckRedundantData(area,date);
                if(cowHealthRedundantData){
                    Snackbar.make(requireView(),"Oops! Such details already exists!",Snackbar.LENGTH_LONG).show();
                }else {
                   boolean cowHealth = poultryHealthDB.SaveCowHealthRecords(batch,area,medication,disease,vaccine,date,affectedBirds,costOfTreatment,vetName);
                   if(cowHealth){
                       Snackbar.make(requireView(),"Poultry Health Record Saved Successfully!",Snackbar.LENGTH_LONG).show();
                   }else{
                       Snackbar.make(requireView(),"Oops! Such details already exists!",Snackbar.LENGTH_LONG).show();
                   }
                }
            }
        });

        return poultryHealthBinding.getRoot();
    }
}