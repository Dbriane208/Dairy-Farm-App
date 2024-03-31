package daniel.brian.dairyfarmapp.employee;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.dairyfarmapp.databinding.FragmentEmployeeBinding;
import daniel.brian.dairyfarmapp.db.EmployeeDetailsDB;

public class EmployeeFragment extends Fragment {
    FragmentEmployeeBinding employeeBinding;
    EmployeeDetailsDB employeeDetailsDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        employeeBinding = FragmentEmployeeBinding.inflate(getLayoutInflater());

        employeeDetailsDB = new EmployeeDetailsDB(this.getContext());

        employeeBinding.saveEmployeeDetails.setOnClickListener(view -> {

            String arrival = Objects.requireNonNull(employeeBinding.arrivalTime.getText()).toString();
            String employeeId = Objects.requireNonNull(employeeBinding.employeeId.getText()).toString();
            String activities = Objects.requireNonNull(employeeBinding.activitiesDone.getText()).toString();
            String gender = Objects.requireNonNull(employeeBinding.gender.getText()).toString();
            String name = Objects.requireNonNull(employeeBinding.workerName.getText()).toString();
            String role = Objects.requireNonNull(employeeBinding.employeeRole.getText()).toString();
            String departure = Objects.requireNonNull(employeeBinding.departureTime.getText()).toString();

            if(
                    arrival.isEmpty() || employeeId.isEmpty() || activities.isEmpty() ||
                    gender.isEmpty() || name.isEmpty() || role.isEmpty() || departure.isEmpty()
            ){
                Snackbar.make(requireView(),"Please Enter Details in All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                boolean employeeDetailsRedundantData = employeeDetailsDB.CheckRedundantData(employeeId,activities);
                if(employeeDetailsRedundantData){
                    Snackbar.make(requireView(),"Oops! Such Details Already Exists!",Snackbar.LENGTH_LONG).show();
                }else{
                    boolean employeeDetails = employeeDetailsDB.SaveEmployeeDetails(arrival,employeeId,activities,gender,name,role,departure);
                    if(employeeDetails){
                        Snackbar.make(requireView(),"Employee Details Saved Successfully!",Snackbar.LENGTH_LONG).show();
                    }else{
                        Snackbar.make(requireView(),"Oops! Such Details Already Exists!",Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        return employeeBinding.getRoot();
    }
}