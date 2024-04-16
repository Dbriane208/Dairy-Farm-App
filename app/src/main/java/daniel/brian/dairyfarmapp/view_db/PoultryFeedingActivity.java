package daniel.brian.dairyfarmapp.view_db;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.adapters.PoultryFeedingAdapter;
import daniel.brian.dairyfarmapp.databinding.ActivityPoultryFeedingBinding;
import daniel.brian.dairyfarmapp.db.PoultryFeedingDB;

public class PoultryFeedingActivity extends AppCompatActivity {
    ActivityPoultryFeedingBinding poultryFeedingBinding;
    RecyclerView recyclerView;
    PoultryFeedingDB poultryFeedingDB;
    PoultryFeedingAdapter poultryFeedingAdapter;
    ArrayList<String> categoryFed, feedType, amFeed, noonFeed, pmFeed, servedBy,totalFeed, costFeed,purchaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        poultryFeedingBinding = ActivityPoultryFeedingBinding.inflate(getLayoutInflater());
        setContentView(poultryFeedingBinding.getRoot());

        poultryFeedingDB = new PoultryFeedingDB(this);
        categoryFed = new ArrayList<>();
        feedType = new ArrayList<>();
        amFeed = new ArrayList<>();
        noonFeed = new ArrayList<>();
        pmFeed = new ArrayList<>();
        servedBy = new ArrayList<>();
        totalFeed = new ArrayList<>();
        costFeed = new ArrayList<>();
        purchaseDate = new ArrayList<>();

        recyclerView = poultryFeedingBinding.poultryFeedingRV;
        poultryFeedingAdapter = new PoultryFeedingAdapter(this,categoryFed, feedType, amFeed, noonFeed, pmFeed, servedBy,totalFeed,costFeed,purchaseDate);
        recyclerView.setAdapter(poultryFeedingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        displayData();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void displayData() {
        Cursor cursor = poultryFeedingDB.getPoultryFeeding();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Items Available", Toast.LENGTH_LONG).show();
        } else {

            while (cursor.moveToNext()) {
                categoryFed.add(cursor.getString(0));
                feedType.add(cursor.getString(1));
                amFeed.add(cursor.getString(2));
                noonFeed.add(cursor.getString(3));
                pmFeed.add(cursor.getString(4));
                servedBy.add(cursor.getString(5));
                totalFeed.add(cursor.getString(6));
                costFeed.add(cursor.getString(7));
                purchaseDate.add(cursor.getString(8));
            }

            // Move the adapter update here to make sure it gets updated with the new data
            poultryFeedingAdapter.notifyDataSetChanged();
        }
    }
}