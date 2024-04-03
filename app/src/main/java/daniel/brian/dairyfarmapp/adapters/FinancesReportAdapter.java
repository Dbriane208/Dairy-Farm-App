package daniel.brian.dairyfarmapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.databinding.FinancesReportRvBinding;

public class FinancesReportAdapter extends RecyclerView.Adapter<FinancesReportAdapter.FinancesReportViewHolder> {

    private ArrayList medicalExpenses, feedExpenses, labourExpenses, expenditure, totalExpenses, totalIncome;
    private Context context;


    public FinancesReportAdapter(Context context, ArrayList medicalExpenses, ArrayList feedExpenses, ArrayList labourExpenses, ArrayList expenditure, ArrayList totalExpenses, ArrayList totalIncome) {
        this.context = context;
        this.medicalExpenses = medicalExpenses;
        this.feedExpenses = feedExpenses;
        this.labourExpenses = labourExpenses;
        this.expenditure = expenditure;
        this.totalExpenses = totalExpenses;
        this.totalIncome = totalIncome;
    }

    @NonNull
    @Override
    public FinancesReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FinancesReportAdapter.FinancesReportViewHolder(
                FinancesReportRvBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull FinancesReportViewHolder holder, int position) {
        holder.financesReportRvBinding.medicalExpenses.setText(String.valueOf(medicalExpenses.get(position)));
        holder.financesReportRvBinding.feedsExpenses.setText(String.valueOf(feedExpenses.get(position)));
        holder.financesReportRvBinding.labourExpenses.setText(String.valueOf(labourExpenses.get(position)));
        holder.financesReportRvBinding.expenditure.setText(String.valueOf(expenditure.get(position)));
        holder.financesReportRvBinding.totalExpenses.setText(String.valueOf(totalExpenses.get(position)));
        holder.financesReportRvBinding.totalIncome.setText(String.valueOf(totalIncome.get(position)));

    }

    @Override
    public int getItemCount() {
        return medicalExpenses.size();
    }

    public static class FinancesReportViewHolder extends RecyclerView.ViewHolder {
        private final FinancesReportRvBinding financesReportRvBinding;

        public FinancesReportViewHolder(FinancesReportRvBinding financesReportRvBinding) {
            super(financesReportRvBinding.getRoot());
            this.financesReportRvBinding = financesReportRvBinding;
        }
    }
}


