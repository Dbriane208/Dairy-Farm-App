package daniel.brian.dairyfarmapp.admin.birds_details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.dairyfarmapp.databinding.FragmentPoultryDetailsBinding;
import daniel.brian.dairyfarmapp.db.PoultryDetailsDB;

public class PoultryDetailsFragment extends Fragment {
    FragmentPoultryDetailsBinding poultryDetailsBinding;
    PoultryDetailsDB poultryDetailsDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        poultryDetailsBinding = FragmentPoultryDetailsBinding.inflate(getLayoutInflater());

        // initializing the database
        poultryDetailsDB = new PoultryDetailsDB(this.getContext());

        // saving details button
        poultryDetailsBinding.saveCowDetails.setOnClickListener(view -> {
            // Getting the cow details
            String layers = Objects.requireNonNull(poultryDetailsBinding.layersBirds.getText()).toString().trim();
            String broilers = Objects.requireNonNull(poultryDetailsBinding.broilerBirds.getText()).toString().trim();
            String total = Objects.requireNonNull(poultryDetailsBinding.totalBirds.getText()).toString().trim();
            String doa = Objects.requireNonNull(poultryDetailsBinding.dateOfAcquisition.getText()).toString().trim();
            String toa = Objects.requireNonNull(poultryDetailsBinding.typeOfAcquisition.getText()).toString().trim();
            String coa = Objects.requireNonNull(poultryDetailsBinding.costOfAcquisition.getText()).toString().trim();

            if(layers.isEmpty() || broilers.isEmpty() || total.isEmpty() || doa.isEmpty() || toa.isEmpty() || coa.isEmpty()){
                Snackbar.make(requireView(),"Please Enter details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else {
                boolean cowDetails = poultryDetailsDB.PoultryRegistration(layers,broilers,total,doa,toa,coa);
                if(cowDetails){
                    Snackbar.make(requireView(),"Poultry Details Saved Successfully!",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(requireView(),"Oops! Such Details Already Exists!",Snackbar.LENGTH_LONG).show();
                }
            }

        });

        return poultryDetailsBinding.getRoot();
    }
}