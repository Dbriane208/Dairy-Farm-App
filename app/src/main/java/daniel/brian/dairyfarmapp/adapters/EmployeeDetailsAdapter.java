package daniel.brian.dairyfarmapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.databinding.EmployeeDetailsBinding;

public class EmployeeDetailsAdapter extends RecyclerView.Adapter<EmployeeDetailsAdapter.EmployeeDetailsViewHolder> {

    private ArrayList arrivalTime, employeeId, gender, employeeName, employeeRole, departureTime;
    private Context context;


    public EmployeeDetailsAdapter(Context context, ArrayList arrivalTime, ArrayList employeeId, ArrayList gender, ArrayList employeeName, ArrayList employeeRole, ArrayList departureTime) {
        this.context = context;
        this.arrivalTime = arrivalTime;
        this.employeeId = employeeId;
        this.gender = gender;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.departureTime = departureTime;
    }

    @NonNull
    @Override
    public EmployeeDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeDetailsAdapter.EmployeeDetailsViewHolder(
                EmployeeDetailsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeDetailsViewHolder holder, int position) {
        holder.employeeDetailsBinding.arrivalTime.setText(String.valueOf(arrivalTime.get(position)));
        holder.employeeDetailsBinding.employeeId.setText(String.valueOf(employeeId.get(position)));
        holder.employeeDetailsBinding.employeeGender.setText(String.valueOf(gender.get(position)));
        holder.employeeDetailsBinding.employeeName.setText(String.valueOf(employeeName.get(position)));
        holder.employeeDetailsBinding.employeeRole.setText(String.valueOf(employeeRole.get(position)));
        holder.employeeDetailsBinding.departureTime.setText(String.valueOf(departureTime.get(position)));
    }

    @Override
    public int getItemCount() {
        return arrivalTime.size();
    }

    public static class EmployeeDetailsViewHolder extends RecyclerView.ViewHolder {
        private final EmployeeDetailsBinding employeeDetailsBinding;

        public EmployeeDetailsViewHolder(EmployeeDetailsBinding employeeDetailsBinding) {
            super(employeeDetailsBinding.getRoot());
            this.employeeDetailsBinding = employeeDetailsBinding;
        }
    }
}



