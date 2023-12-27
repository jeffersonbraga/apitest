package com.texo.apitest.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "producer_model")
public class ProducerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer producerId;

    private String producerName;

    @ManyToMany
    @JoinTable(
            name="movie_producer_model",
            joinColumns = @JoinColumn(name = "producer_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<MovieModel> moviesProduced;

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

    public ProducerModel() {
    }
}
