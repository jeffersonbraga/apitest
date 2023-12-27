package com.texo.apitest.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "movie_model")
public class MovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer movieId;

    private Integer movieYear;

    private String movieName;

    private Boolean winner;

    @ManyToMany
    @JoinTable(
            name="movie_producer_model",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id"))
    private List<ProducerModel> movieProducer;

    @ManyToMany
    @PrimaryKeyJoinColumn
    private List<StudioModel> movieStudio;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public List<ProducerModel> getMovieProducers() {
        return movieProducer;
    }

    public void setMovieProducers(List<ProducerModel> movieProducerModels) {
        this.movieProducer = movieProducerModels;
    }

    public List<StudioModel> getMovieStudios() {
        return movieStudio;
    }

    public void setMovieStudios(List<StudioModel> movieStudioModels) {
        this.movieStudio = movieStudioModels;
    }

    public Integer getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(Integer movieYear) {
        this.movieYear = movieYear;
    }
}
