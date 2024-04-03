package daniel.brian.dairyfarmapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.databinding.EggsSaleRvBinding;

public class EggsSalesAdapter extends RecyclerView.Adapter<EggsSalesAdapter.EggsSaleViewHolder> {

    private ArrayList sellingDate, clientName, clientPhone, eggsQuantity, eggsPrice, totalAmount;
    private Context context;


    public EggsSalesAdapter(Context context, ArrayList sellingDate, ArrayList clientName, ArrayList clientPhone, ArrayList eggsQuantity, ArrayList eggsPrice, ArrayList totalAmount) {
        this.context = context;
        this.sellingDate = sellingDate;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.eggsQuantity = eggsQuantity;
        this.eggsPrice = eggsPrice;
        this.totalAmount = totalAmount;
    }

    @NonNull
    @Override
    public EggsSaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EggsSalesAdapter.EggsSaleViewHolder(
                EggsSaleRvBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull EggsSaleViewHolder holder, int position) {
        holder.eggsSaleRvBinding.sellingDate.setText(String.valueOf(sellingDate.get(position)));
        holder.eggsSaleRvBinding.clientName.setText(String.valueOf(clientName.get(position)));
        holder.eggsSaleRvBinding.clientPhone.setText(String.valueOf(clientPhone.get(position)));
        holder.eggsSaleRvBinding.eggsQuantity.setText(String.valueOf(eggsQuantity.get(position)));
        holder.eggsSaleRvBinding.eggsPrice.setText(String.valueOf(eggsPrice.get(position)));
        holder.eggsSaleRvBinding.totalAmount.setText(String.valueOf(totalAmount.get(position)));
    }

    @Override
    public int getItemCount() {
        return sellingDate.size();
    }

    public static class EggsSaleViewHolder extends RecyclerView.ViewHolder {
        private final EggsSaleRvBinding eggsSaleRvBinding;

        public EggsSaleViewHolder(EggsSaleRvBinding eggsSaleRvBinding) {
            super(eggsSaleRvBinding.getRoot());
            this.eggsSaleRvBinding = eggsSaleRvBinding;
        }
    }
}



