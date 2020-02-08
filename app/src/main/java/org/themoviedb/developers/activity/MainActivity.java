package org.themoviedb.developers.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.themoviedb.developers.BuildConfig;
import org.themoviedb.developers.R;
import org.themoviedb.developers.adapter.MovieAdapter;
import org.themoviedb.developers.api.Client;
import org.themoviedb.developers.api.Service;
import org.themoviedb.developers.model.Movie;
import org.themoviedb.developers.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Blanca Rangel{@literal <blazuhier at gmail dot com>}
 * @version $Revision: 1 $ $Date: 2020-02-05 20:19:46 -0500 (Wed, 05 Feb 2020) $
 */
public class MainActivity extends AppCompatActivity {
    //~Instance attributes =========================================================================
    private RecyclerView recyclerViewr;
    private MovieAdapter adapter;
    private List<Movie> movieList;
    ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    //~Static attributes ===========================================================================
    public static  final String LOG_TAG = MovieAdapter.class.getName();

    //~Instance methods ===========================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        swipeRefreshLayout = findViewById(R.id.main_content);
        swipeRefreshLayout.setColorSchemeResources(R.color.highlighted);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews();
                Toast.makeText(MainActivity.this, "Peliculas Actualizadas",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public Activity getActivity(){
        Context context = this;
        while (context instanceof ContextWrapper){
            if(context instanceof Activity){
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    private void initViews(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Raiting de películas...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        recyclerViewr = findViewById(R.id.recycler_view);
        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);

        if(getActivity().getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT){
            recyclerViewr.setLayoutManager(new GridLayoutManager(this, 2));
        }else{
            recyclerViewr.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerViewr.setItemAnimator(new DefaultItemAnimator());
        recyclerViewr.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getDataMovie();

    }

    private void getDataMovie(){

        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, primero obtenga su API KEY en themoviedb.org ",
                        Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                return;
            }

            Client client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<MovieResponse> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    if(response.body()!= null) {
                        List<Movie> movies = response.body().getResults();
                        recyclerViewr.setAdapter(new MovieAdapter(getApplicationContext(), movies));
                        recyclerViewr.smoothScrollToPosition(0);
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Datos nulos",
                                Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error al cargar los datos",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception ex){
            Log.d("Error", ex.getMessage());
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }


}
