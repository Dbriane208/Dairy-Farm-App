package daniel.brian.dairyfarmapp.admin.cow_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.dairyfarmapp.databinding.FragmentHealthBinding;
import daniel.brian.dairyfarmapp.db.CowHealthDB;

public class HealthFragment extends Fragment {
    FragmentHealthBinding healthBinding;
    CowHealthDB cowHealthDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        healthBinding = FragmentHealthBinding.inflate(getLayoutInflater());

        // Declaring the cowHealthDB
        cowHealthDB = new CowHealthDB(this.getContext());

        // saving the health record
        healthBinding.saveHealthDetails.setOnClickListener(view -> {
            // accessing the details
            String cowName = Objects.requireNonNull(healthBinding.cowName.getText()).toString();
            String event = Objects.requireNonNull(healthBinding.healthEvent.getText()).toString();
            String diagnosis = Objects.requireNonNull(healthBinding.healthDiagnosis.getText()).toString();
            String date = Objects.requireNonNull(healthBinding.healthDate.getText()).toString();
            String treatment = Objects.requireNonNull(healthBinding.healthTreatment.getText()).toString();
            String costOfTreatment = Objects.requireNonNull(healthBinding.treatmentCost.getText()).toString();
            String vetName = Objects.requireNonNull(healthBinding.vetName.getText()).toString();

            if(cowName.isEmpty() || event.isEmpty() || diagnosis.isEmpty() || date.isEmpty() || treatment.isEmpty() || costOfTreatment.isEmpty() || vetName.isEmpty()){
                Snackbar.make(requireView(),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else {
                boolean cowHealthRedundantData = cowHealthDB.CheckRedundantData(cowName,date);
                if(cowHealthRedundantData){
                    Snackbar.make(requireView(),"Oops! Such details already exists!",Snackbar.LENGTH_LONG).show();
                }else {
                   boolean cowHealth = cowHealthDB.SaveCowHealthRecords(cowName,event,diagnosis,date,treatment,costOfTreatment,vetName);
                   if(cowHealth){
                       Snackbar.make(requireView(),"Cow Health Record Saved Successfully!",Snackbar.LENGTH_LONG).show();
                   }else{
                       Snackbar.make(requireView(),"Oops! Such details already exists!",Snackbar.LENGTH_LONG).show();
                   }
                }
            }
        });

        return healthBinding.getRoot();
    }
}