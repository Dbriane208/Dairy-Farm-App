package daniel.brian.dairyfarmapp.admin.birds_details;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import daniel.brian.dairyfarmapp.R;
import daniel.brian.dairyfarmapp.databinding.FragmentDatabasesBinding;
import daniel.brian.dairyfarmapp.view_db.ChickenSalesActivity;
import daniel.brian.dairyfarmapp.view_db.EggsSaleActivity;
import daniel.brian.dairyfarmapp.view_db.EmployeeDetailsActivity;
import daniel.brian.dairyfarmapp.view_db.FinancesReportActivity;
import daniel.brian.dairyfarmapp.view_db.PoultryDetailsActivity;
import daniel.brian.dairyfarmapp.view_db.PoultryFeedingActivity;
import daniel.brian.dairyfarmapp.view_db.PoultryHealthActivity;
import daniel.brian.dairyfarmapp.view_db.PoultryProductionActivity;

public class DatabasesFragment extends Fragment {
    FragmentDatabasesBinding databasesBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        databasesBinding = FragmentDatabasesBinding.inflate(getLayoutInflater());

        // navigating to poultry details db
        databasesBinding.poultryDetailsView.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), PoultryDetailsActivity.class);
            startActivity(intent);
        });

        // navigating to poultry prod db
        databasesBinding.poultryProdView.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), PoultryProductionActivity.class);
            startActivity(intent);
        });

        // navigating to poultry health db
        databasesBinding.poultryHealthView.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), PoultryHealthActivity.class);
            startActivity(intent);
        });

        // navigating to poultry feeding db
        databasesBinding.poultryFeedingView.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), PoultryFeedingActivity.class);
            startActivity(intent);
        });

        // navigating to poultry finances report
        databasesBinding.poultryFinancesView.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), FinancesReportActivity.class);
            startActivity(intent);
        });

        // navigating to eggs sales db
        databasesBinding.poultryEggsSaleView.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EggsSaleActivity.class);
            startActivity(intent);
        });

        // navigating to chicken sales db
        databasesBinding.poultryChickenSalesView.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChickenSalesActivity.class);
            startActivity(intent);
        });

        // navigating to employee details
        databasesBinding.employeeDetailsView.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EmployeeDetailsActivity.class);
            startActivity(intent);
        });

        return databasesBinding.getRoot();
    }
}