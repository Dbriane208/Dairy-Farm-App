package daniel.brian.dairyfarmapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.databinding.ChickenSalesRvBinding;

public class ChickenSalesAdapter extends RecyclerView.Adapter<ChickenSalesAdapter.ChickenSalesViewHolder> {

    private ArrayList sellingDateC, clientNameC, clientPhoneC, chickenQuantity, chickenPrice, totalAmountC;
    private Context context;


    public ChickenSalesAdapter(Context context, ArrayList sellingDateC, ArrayList clientNameC, ArrayList clientPhoneC, ArrayList chickenQuantity, ArrayList chickenPrice, ArrayList totalAmountC) {
        this.context = context;
        this.sellingDateC = sellingDateC;
        this.clientNameC = clientNameC;
        this.clientPhoneC = clientPhoneC;
        this.chickenQuantity = chickenQuantity;
        this.chickenPrice = chickenPrice;
        this.totalAmountC = totalAmountC;
    }

    @NonNull
    @Override
    public ChickenSalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChickenSalesAdapter.ChickenSalesViewHolder(
                ChickenSalesRvBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ChickenSalesViewHolder holder, int position) {
        holder.chickenSalesRvBinding.sellingDateChicken.setText(String.valueOf(sellingDateC.get(position)));
        holder.chickenSalesRvBinding.clientNameChicken.setText(String.valueOf(clientNameC.get(position)));
        holder.chickenSalesRvBinding.clientPhoneChicken.setText(String.valueOf(clientPhoneC.get(position)));
        holder.chickenSalesRvBinding.chickenQuantity.setText(String.valueOf(chickenQuantity.get(position)));
        holder.chickenSalesRvBinding.chickenPrice.setText(String.valueOf(chickenPrice.get(position)));
        holder.chickenSalesRvBinding.totalAmountChicken.setText(String.valueOf(totalAmountC.get(position)));

    }


    @Override
    public int getItemCount() {
        return totalAmountC.size();
    }

    public static class ChickenSalesViewHolder extends RecyclerView.ViewHolder {
        private final ChickenSalesRvBinding chickenSalesRvBinding;

        public ChickenSalesViewHolder(ChickenSalesRvBinding chickenSalesRvBinding) {
            super(chickenSalesRvBinding.getRoot());
            this.chickenSalesRvBinding = chickenSalesRvBinding;
        }
    }
}



