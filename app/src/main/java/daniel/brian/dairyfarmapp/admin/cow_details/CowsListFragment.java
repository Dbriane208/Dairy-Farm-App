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
import daniel.brian.dairyfarmapp.databinding.FragmentCowsListBinding;
import daniel.brian.dairyfarmapp.db.CowDetailsDB;

public class CowsListFragment extends Fragment {
    FragmentCowsListBinding cowsListBinding;
    CowDetailsDB cowDetailsDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        cowsListBinding = FragmentCowsListBinding.inflate(getLayoutInflater());

        // initializing the database
        cowDetailsDB = new CowDetailsDB(this.getContext());

        // saving details button
        cowsListBinding.saveCowDetails.setOnClickListener(view -> {
            // Getting the cow details
            String cowName = Objects.requireNonNull(cowsListBinding.cowName.getText()).toString().trim();
            String earTag = Objects.requireNonNull(cowsListBinding.earTag.getText()).toString().trim();
            String cowColor = Objects.requireNonNull(cowsListBinding.cowColor.getText()).toString().trim();
            String cowBreed = Objects.requireNonNull(cowsListBinding.cowBreed.getText()).toString().trim();
            String cowDOB = Objects.requireNonNull(cowsListBinding.cowDOB.getText()).toString().trim();
            String cowAge = Objects.requireNonNull(cowsListBinding.cowAge.getText()).toString().trim();
            String cowWAT = Objects.requireNonNull(cowsListBinding.cowWAT.getText()).toString().trim();

            if(cowName.isEmpty() || earTag.isEmpty() || cowColor.isEmpty() || cowBreed.isEmpty() || cowDOB.isEmpty() || cowAge.isEmpty() || cowWAT.isEmpty()){
                Snackbar.make(requireView(),"Please Enter details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else {
                boolean cowDetails = cowDetailsDB.CowRegistration(cowName,earTag,cowColor,cowBreed,cowDOB,cowAge,cowWAT);
                if(cowDetails){
                    Snackbar.make(requireView(),"Cow Details Saved Successfully!",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(requireView(),"Oops! Such Details Already Exists!",Snackbar.LENGTH_LONG).show();
                }
            }

        });

        return cowsListBinding.getRoot();
    }
}