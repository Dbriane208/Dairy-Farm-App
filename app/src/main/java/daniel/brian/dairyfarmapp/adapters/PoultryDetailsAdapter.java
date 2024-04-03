package daniel.brian.dairyfarmapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.databinding.PoultryDetailsRvBinding;

public class PoultryDetailsAdapter extends RecyclerView.Adapter<PoultryDetailsAdapter.PoultryDetailsViewHolder> {
    private ArrayList layersBirds,broilerBirds,totalBirds,acquisitionDate,acquisitionType,acquisitionCost;
    private Context context;
    @NonNull
    @Override
    public PoultryDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PoultryDetailsViewHolder(
                PoultryDetailsRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }


    public PoultryDetailsAdapter(Context context,ArrayList layersBirds,ArrayList broilerBirds,ArrayList totalBirds,ArrayList acquisitionDate, ArrayList acquisitionType, ArrayList acquisitionCost){
        this.context = context;
        this.layersBirds = layersBirds;
        this.broilerBirds = broilerBirds;
        this.totalBirds = totalBirds;
        this.acquisitionDate = acquisitionDate;
        this.acquisitionType = acquisitionType;
        this.acquisitionCost = acquisitionCost;
    }

    @Override
    public void onBindViewHolder(@NonNull PoultryDetailsViewHolder holder, int position) {
        holder.poultryDetailsRvBinding.layerBirds.setText(String.valueOf(layersBirds.get(position)));
        holder.poultryDetailsRvBinding.broilerBirds.setText(String.valueOf(broilerBirds.get(position)));
        holder.poultryDetailsRvBinding.totalBirds.setText(String.valueOf(totalBirds.get(position)));
        holder.poultryDetailsRvBinding.acquisitionDate.setText(String.valueOf(acquisitionDate.get(position)));
        holder.poultryDetailsRvBinding.acquisitionType.setText(String.valueOf(acquisitionType.get(position)));
        holder.poultryDetailsRvBinding.acquisitionCost.setText(String.valueOf(acquisitionCost.get(position)));
    }

    @Override
    public int getItemCount() {
        return layersBirds.size();
    }

    public static class PoultryDetailsViewHolder extends RecyclerView.ViewHolder{
        private final PoultryDetailsRvBinding poultryDetailsRvBinding;
        public PoultryDetailsViewHolder(PoultryDetailsRvBinding poultryDetailsRvBinding) {
            super(poultryDetailsRvBinding.getRoot());
            this.poultryDetailsRvBinding = poultryDetailsRvBinding;
        }
    }
}
