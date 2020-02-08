package org.themoviedb.developers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("video")
    private Boolean video;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("id")
    private Integer id;

    @SerializedName("adult")
    private Boolean adult;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("original_languaje")
    private String originalLanguaje;

    @SerializedName("originalTitle")
    private String originalTitle;

    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<>();

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private Double voteAverage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    public Movie(Double popularity, Integer voteCount, Boolean video, String posterPath, Integer id, Boolean adult, String backdropPath, String originalLanguaje, String originalTitle, List<Integer> genreIds, String title, Double voteAverage, String overview, String releaseDate) {
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.posterPath = posterPath;
        this.id = id;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.originalLanguaje = originalLanguaje;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.title = title;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    String baseImgUrl = "https://image.tmdb.org/t/p/w500";

    public Double getPopularity() {
        return popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOriginalLanguaje() {
        return originalLanguaje;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getTitle() {
        return title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
