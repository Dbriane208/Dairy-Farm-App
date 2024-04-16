package daniel.brian.dairyfarmapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.databinding.PoultryProductionRvBinding;

public class PoultryProductionAdapter extends RecyclerView.Adapter<PoultryProductionAdapter.PoultryProductionViewHolder> {

    private ArrayList batchName, meatProduction, servedBy, timeProduced, collectionArea, collectionDate,totalEggsCollected,goodEggsCollected,brokenEggsCollected,totalTraysCollected;
    private Context context;


    public PoultryProductionAdapter(Context context, ArrayList batchName, ArrayList meatProduction, ArrayList servedBy, ArrayList timeProduced, ArrayList collectionArea, ArrayList collectionDate,
    ArrayList totalEggsCollected,ArrayList goodEggsCollected, ArrayList brokenEggsCollected, ArrayList totalTraysCollected) {
        this.context = context;
        this.batchName = batchName;
        this.meatProduction = meatProduction;
        this.servedBy = servedBy;
        this.timeProduced = timeProduced;
        this.collectionArea = collectionArea;
        this.collectionDate = collectionDate;
        this.totalEggsCollected = totalEggsCollected;
        this.goodEggsCollected = goodEggsCollected;
        this.brokenEggsCollected = brokenEggsCollected;
        this.totalTraysCollected = totalTraysCollected;
    }

    @NonNull
    @Override
    public PoultryProductionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PoultryProductionViewHolder(
                PoultryProductionRvBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PoultryProductionViewHolder holder, int position) {
        holder.poultryProductionRvBinding.batchName.setText(String.valueOf(batchName.get(position)));
        holder.poultryProductionRvBinding.meatProduction.setText(String.valueOf(meatProduction.get(position)));
        holder.poultryProductionRvBinding.servedBy.setText(String.valueOf(servedBy.get(position)));
        holder.poultryProductionRvBinding.timeProduced.setText(String.valueOf(timeProduced.get(position)));
        holder.poultryProductionRvBinding.collectionArea.setText(String.valueOf(collectionArea.get(position)));
        holder.poultryProductionRvBinding.collectionDate.setText(String.valueOf(collectionDate.get(position)));
        holder.poultryProductionRvBinding.eggsCollected.setText(String.valueOf(totalEggsCollected.get(position)));
        holder.poultryProductionRvBinding.goodEggs.setText(String.valueOf(goodEggsCollected.get(position)));
        holder.poultryProductionRvBinding.brokenEggs.setText(String.valueOf(brokenEggsCollected.get(position)));
        holder.poultryProductionRvBinding.totalTrays.setText(String.valueOf(totalTraysCollected.get(position)));

    }

    @Override
    public int getItemCount() {
        return batchName.size();
    }

    public static class PoultryProductionViewHolder extends RecyclerView.ViewHolder {
        private final PoultryProductionRvBinding poultryProductionRvBinding;

        public PoultryProductionViewHolder(PoultryProductionRvBinding poultryProductionRvBinding) {
            super(poultryProductionRvBinding.getRoot());
            this.poultryProductionRvBinding = poultryProductionRvBinding;
        }
    }
}


