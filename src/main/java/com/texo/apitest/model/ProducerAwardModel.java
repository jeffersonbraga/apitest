package com.texo.apitest.model;


import jakarta.persistence.*;

@Entity
@Table(name = "producers_awards")
public class ProducerAwardModel {

    @Id
    private String id;

    private Integer movieId;

    private Integer movie_year;

    private String movie_name;

    private Boolean winner;

    private Integer producerId;

    private String producerName;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getMovie_year() {
        return movie_year;
    }

    public void setMovie_year(Integer movie_year) {
        this.movie_year = movie_year;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }
}
