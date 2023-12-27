package com.texo.apitest.services;

import com.texo.apitest.enums.RecordPositions;
import com.texo.apitest.model.MovieModel;
import com.texo.apitest.model.ProducerModel;
import com.texo.apitest.model.StudioModel;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessModelFromCsv {

    public MovieModel createModel(List<String> dados, ArrayList<Pair<String, StudioModel>> pairMappingStudios, ArrayList<Pair<String, ProducerModel>> pairMappingProducers) {
        MovieModel movieModel = new MovieModel();
        movieModel.setMovieYear(Integer.parseInt(dados.get(RecordPositions.YEAR.position)));
        movieModel.setMovieName(dados.get(RecordPositions.TITLE.position));
        Boolean winner = (dados.size() < 5 || dados.get(RecordPositions.WINNER.position).isEmpty())?Boolean.FALSE:Boolean.TRUE;
        movieModel.setWinner(winner);
        movieModel.setMovieStudios(new ArrayList<>());
        movieModel.setMovieProducers(new ArrayList<>());
        this.processStudios(movieModel, dados.get(RecordPositions.STUDIOS.position), pairMappingStudios);
        this.processProducers(movieModel, dados.get(RecordPositions.PRODUCERS.position), pairMappingProducers);
        return movieModel;
    }

    private void processStudios(MovieModel movieModel, String studioRow, ArrayList<Pair<String, StudioModel>> pairMappingStudios) {

        for (String studioItem : studioRow.split(",")) {
            String studioName = studioItem.trim();
            if (!studioName.isEmpty()) {
                if (pairMappingStudios.stream().anyMatch(addedStudio -> addedStudio.getFirst().equals(studioName))) {
                    StudioModel studioModel = pairMappingStudios
                            .stream()
                            .filter(addedStudio -> addedStudio.getFirst().equals(studioName))
                            .findFirst()
                            .orElseThrow()
                            .getSecond();
                    movieModel.getMovieStudios().add(studioModel);
                } else {
                    this.createStudioModel(studioName, movieModel, pairMappingStudios);
                }
            }
        }
    }

    private void createStudioModel(String studioName, MovieModel movieModel, ArrayList<Pair<String, StudioModel>> pairMappingStudios) {
        StudioModel studioModel = new StudioModel();
        studioModel.setStudioName(studioName);
        movieModel.getMovieStudios().add(studioModel);
        Pair<String, StudioModel> pairItemStudio = Pair.of(studioName, studioModel);
        pairMappingStudios.add(pairItemStudio);
    }

    private void processProducers(MovieModel movieModel, String producerRow, ArrayList<Pair<String, ProducerModel>> pairMappingProducers) {
        for (String producerItem : producerRow.split("( and |,)")) {
            String producerName = producerItem.trim();
            if (!producerName.isEmpty()) {
                if (pairMappingProducers.stream().anyMatch(addedProducer -> addedProducer.getFirst().equals(producerName))) {
                    ProducerModel producerModel = pairMappingProducers
                            .stream()
                            .filter(addedProducer -> addedProducer.getFirst().equals(producerName))
                            .findFirst()
                            .orElseThrow()
                            .getSecond();
                    movieModel.getMovieProducers().add(producerModel);
                } else {
                    this.createProducerModel(producerName, movieModel, pairMappingProducers);
                }
            }
        }
    }

    private void createProducerModel(String producerName, MovieModel movieModel, ArrayList<Pair<String, ProducerModel>> pairMappingProducers) {

        ProducerModel producerModel = new ProducerModel();
        producerModel.setProducerName(producerName);
        movieModel.getMovieProducers().add(producerModel);
        Pair<String, ProducerModel> pairItemProducer = Pair.of(producerName, producerModel);
        pairMappingProducers.add(pairItemProducer);
    }
}
