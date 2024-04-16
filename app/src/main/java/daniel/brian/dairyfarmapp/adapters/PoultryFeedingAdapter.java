package daniel.brian.dairyfarmapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.databinding.PoultryFeedingRvBinding;

public class PoultryFeedingAdapter extends RecyclerView.Adapter<PoultryFeedingAdapter.PoultryFeedingViewHolder> {

    private ArrayList categoryFed, feedType, amFeed, noonFeed, pmFeed,servedBy,totalFeed, costFeed,purchaseDate;
    private Context context;


    public PoultryFeedingAdapter(Context context, ArrayList categoryFed, ArrayList feedType, ArrayList amFeed, ArrayList noonFeed, ArrayList pmFeed,ArrayList servedBy,ArrayList amountFeed, ArrayList costFeed,ArrayList purchaseDate) {
        this.context = context;
        this.categoryFed = categoryFed;
        this.feedType = feedType;
        this.amFeed = amFeed;
        this.noonFeed = noonFeed;
        this.pmFeed = pmFeed;
        this.servedBy = servedBy;
        this.totalFeed = amountFeed;
        this.costFeed = costFeed;
        this.purchaseDate = purchaseDate;
    }

    @NonNull
    @Override
    public PoultryFeedingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PoultryFeedingAdapter.PoultryFeedingViewHolder(
                PoultryFeedingRvBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PoultryFeedingViewHolder holder, int position) {
        holder.poultryFeedingRvBinding.categoryFed.setText(String.valueOf(categoryFed.get(position)));
        holder.poultryFeedingRvBinding.feedType.setText(String.valueOf(feedType.get(position)));
        holder.poultryFeedingRvBinding.amFeed.setText(String.valueOf(amFeed.get(position)));
        holder.poultryFeedingRvBinding.noonFeed.setText(String.valueOf(noonFeed.get(position)));
        holder.poultryFeedingRvBinding.pmFeed.setText(String.valueOf(pmFeed.get(position)));
        holder.poultryFeedingRvBinding.servedBy.setText(String.valueOf(servedBy.get(position)));
        holder.poultryFeedingRvBinding.totalFeed.setText(String.valueOf(totalFeed.get(position)));
        holder.poultryFeedingRvBinding.feedCost.setText(String.valueOf(costFeed.get(position)));
        holder.poultryFeedingRvBinding.purchaseDate.setText(String.valueOf(purchaseDate.get(position)));

    }


    @Override
    public int getItemCount() {
        return categoryFed.size();
    }

    public static class PoultryFeedingViewHolder extends RecyclerView.ViewHolder {
        private final PoultryFeedingRvBinding poultryFeedingRvBinding;

        public PoultryFeedingViewHolder(PoultryFeedingRvBinding poultryFeedingRvBinding) {
            super(poultryFeedingRvBinding.getRoot());
            this.poultryFeedingRvBinding = poultryFeedingRvBinding;
        }
    }
}
