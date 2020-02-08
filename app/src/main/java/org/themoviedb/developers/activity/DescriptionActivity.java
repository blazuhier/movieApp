package org.themoviedb.developers.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.themoviedb.developers.R;

public class DescriptionActivity extends AppCompatActivity {

    TextView nameMovie, platSynopsis, userRating, releaseDate;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initCollapsingToolbar();

        imageView = findViewById(R.id.thumbnail);
        nameMovie = findViewById(R.id.titleMovie);
        platSynopsis = findViewById(R.id.synopsis);
        userRating = findViewById(R.id.userRating);
        releaseDate = findViewById(R.id.releaseDate);

        Intent intentActivity = getIntent();
        if(intentActivity.hasExtra("original_title")){
            String thumbnail= getIntent().getExtras().getString("poster_path");
            String movieName = getIntent().getExtras().getString("original_title");
            String synopsis = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String dateOfRelease = getIntent().getExtras().getString("release_date");

            Glide.with(this)
                    .load(thumbnail)
                    .placeholder(R.drawable.load)
                    .into(imageView);

            nameMovie.setText(movieName);
            platSynopsis.setText(synopsis);
            userRating.setText(rating);
            releaseDate.setText(dateOfRelease);

        }else{
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout =
                findViewById(R.id.collapsing);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout = findViewById(R.id.appBar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            Boolean show = false;
            int range = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(range == -1){
                    range= appBarLayout.getTotalScrollRange();
                }
                if(range+ verticalOffset==0){
                    collapsingToolbarLayout.setTitle(getString(R.string.description));
                    show = true;
                }else if(show){
                    collapsingToolbarLayout.setTitle("");
                    show = false;
                }
            }
        });
    }
}
