package daniel.brian.dairyfarmapp.view_db;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.dairyfarmapp.R;
import daniel.brian.dairyfarmapp.adapters.PoultryFeedingAdapter;
import daniel.brian.dairyfarmapp.adapters.PoultryHealthAdapter;
import daniel.brian.dairyfarmapp.databinding.ActivityPoultryFeedingBinding;
import daniel.brian.dairyfarmapp.databinding.ActivityPoultryHealthBinding;
import daniel.brian.dairyfarmapp.db.PoultryFeedingDB;
import daniel.brian.dairyfarmapp.db.PoultryHealthDB;

public class PoultryFeedingActivity extends AppCompatActivity {
    ActivityPoultryFeedingBinding poultryFeedingBinding;
    RecyclerView recyclerView;
    PoultryFeedingDB poultryFeedingDB;
    PoultryFeedingAdapter poultryFeedingAdapter;
    ArrayList<String> categoryFed, feedType, amFeed, noonFeed, pmFeed, costFeed;

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
        costFeed = new ArrayList<>();

        recyclerView = poultryFeedingBinding.poultryFeedingRV;
        poultryFeedingAdapter = new PoultryFeedingAdapter(this,categoryFed, feedType, amFeed, noonFeed, pmFeed, costFeed);
        recyclerView.setAdapter(poultryFeedingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        displayData();
    }

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
                costFeed.add(cursor.getString(5));
            }

            // Move the adapter update here to make sure it gets updated with the new data
            poultryFeedingAdapter.notifyDataSetChanged();
        }
    }
}