package com.texo.apitest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "studio_model")
public class StudioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studioId;

    private String studioName;

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }
}
