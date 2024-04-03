package daniel.brian.dairyfarmapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.databinding.PoultryHealthRvBinding;

public class PoultryHealthAdapter extends RecyclerView.Adapter<PoultryHealthAdapter.PoultryHealthViewHolder> {

    private ArrayList batchNameHealth, categoryAffected, vaccination, diseaseName, vaccineName, date;
    private Context context;


    public PoultryHealthAdapter(Context context, ArrayList batchNameHealth, ArrayList categoryAffected, ArrayList vaccination, ArrayList diseaseName, ArrayList vaccineName, ArrayList date) {
        this.context = context;
        this.batchNameHealth = batchNameHealth;
        this.categoryAffected = categoryAffected;
        this.vaccination = vaccination;
        this.diseaseName = diseaseName;
        this.vaccineName = vaccineName;
        this.date = date;
    }

    @NonNull
    @Override
    public PoultryHealthAdapter.PoultryHealthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PoultryHealthAdapter.PoultryHealthViewHolder(
                PoultryHealthRvBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PoultryHealthViewHolder holder, int position) {
        holder.poultryHealthRvBinding.batchNameHealth.setText(String.valueOf(batchNameHealth.get(position)));
        holder.poultryHealthRvBinding.categoryAffected.setText(String.valueOf(categoryAffected.get(position)));
        holder.poultryHealthRvBinding.vacOrMed.setText(String.valueOf(vaccination.get(position)));
        holder.poultryHealthRvBinding.diseaseName.setText(String.valueOf(diseaseName.get(position)));
        holder.poultryHealthRvBinding.nameOfVaccine.setText(String.valueOf(vaccineName.get(position)));
        holder.poultryHealthRvBinding.healthDate.setText(String.valueOf(date.get(position)));

    }

    @Override
    public int getItemCount() {
        return batchNameHealth.size();
    }

    public static class PoultryHealthViewHolder extends RecyclerView.ViewHolder {
        private final PoultryHealthRvBinding poultryHealthRvBinding;

        public PoultryHealthViewHolder(PoultryHealthRvBinding poultryHealthRvBinding) {
            super(poultryHealthRvBinding.getRoot());
            this.poultryHealthRvBinding = poultryHealthRvBinding;
        }
    }
}
