package daniel.brian.dairyfarmapp.admin.birds_details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import daniel.brian.dairyfarmapp.R;
import daniel.brian.dairyfarmapp.databinding.FragmentAdminHomeBinding;

public class AdminHomeFragment extends Fragment {
    FragmentAdminHomeBinding fragmentAdminHomeBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAdminHomeBinding = FragmentAdminHomeBinding.inflate(getLayoutInflater());

        // adding the buttons logic
        // cowList fragment
        fragmentAdminHomeBinding.poultryDetailsAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminHomeFragment_to_cowsListFragment);
        });

        // milk Prod fragment
        fragmentAdminHomeBinding.poultryProdAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminHomeFragment_to_milkProductionFragment);
        });

        // cow health
        fragmentAdminHomeBinding.poultryHealthAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminHomeFragment_to_healthFragment);
        });

        // cow breeding
        fragmentAdminHomeBinding.poultryFeedAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminHomeFragment_to_cowBreedingFragment);
        });

        //finances
        fragmentAdminHomeBinding.poultryFinancesAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminHomeFragment_to_financesFragment);
        });

        // databases
        fragmentAdminHomeBinding.databasesAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminHomeFragment_to_databasesFragment2);
        });

        return fragmentAdminHomeBinding.getRoot();
    }
}