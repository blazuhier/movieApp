package org.themoviedb.developers.service;

import org.themoviedb.developers.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {

    @GET("movie/550")
    Call<List<Movie>> getMovies();
}
