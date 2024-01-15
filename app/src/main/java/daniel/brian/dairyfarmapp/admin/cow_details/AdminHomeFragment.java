package daniel.brian.dairyfarmapp.admin.cow_details;

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
        fragmentAdminHomeBinding.cowListAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminHomeFragment_to_cowsListFragment);
        });

        // milk Prod fragment
        fragmentAdminHomeBinding.milkProdAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminHomeFragment_to_milkProductionFragment);
        });

        // cow health
        fragmentAdminHomeBinding.cowHealthAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminHomeFragment_to_healthFragment);
        });

        // cow breeding
        fragmentAdminHomeBinding.cowBreedAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminHomeFragment_to_cowBreedingFragment);
        });

        //finances
        fragmentAdminHomeBinding.cowFinancesAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminHomeFragment_to_financesFragment);
        });

        return fragmentAdminHomeBinding.getRoot();
    }
}