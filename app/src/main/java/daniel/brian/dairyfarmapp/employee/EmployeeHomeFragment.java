package daniel.brian.dairyfarmapp.employee;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import daniel.brian.dairyfarmapp.R;
import daniel.brian.dairyfarmapp.databinding.FragmentEmployeeHomeBinding;

public class EmployeeHomeFragment extends Fragment {
    FragmentEmployeeHomeBinding employeeHomeBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        employeeHomeBinding = FragmentEmployeeHomeBinding.inflate(getLayoutInflater());

        // Navigating to the eggs sales
        employeeHomeBinding.eggsSaleAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_employeeHomeFragment_to_eggsSalesFragment);
        });

        // Navigating to the chicken sales
        employeeHomeBinding.chickenSalesAdd.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_employeeHomeFragment_to_chickenSalesFragment);
        });

        // Navigating to employee details
        employeeHomeBinding.employeeDetailsAdd.setOnClickListener(view1 -> {
            NavController controller = Navigation.findNavController(view1);
            controller.navigate(R.id.action_employeeHomeFragment_to_employeeFragment);

        });

        return employeeHomeBinding.getRoot();
    }
}