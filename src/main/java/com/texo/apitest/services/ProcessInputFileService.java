package com.texo.apitest.services;

import com.texo.apitest.model.MovieModel;
import com.texo.apitest.model.ProducerModel;
import com.texo.apitest.model.StudioModel;
import com.texo.apitest.repository.MovieRepository;
import com.texo.apitest.repository.ProducerRepository;
import com.texo.apitest.repository.StudioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class ProcessInputFileService {

    private final ArrayList<Pair<String, StudioModel>> pairMappingStudios = new ArrayList<>();

    private final  ArrayList<Pair<String, ProducerModel>> pairMappingProducers = new ArrayList<>();

    private final  StudioRepository studioRepository;

    private final  ProducerRepository producerRepository;

    private final  MovieRepository movieRepository;

    private final  ProcessModelFromCsv processModelFromCsv;

    public ProcessInputFileService(
            ProcessModelFromCsv processModelFromCsv,
            StudioRepository studioRepository,
            ProducerRepository producerRepository,
            MovieRepository movieRepository) {
        this.processModelFromCsv = processModelFromCsv;
        this.studioRepository = studioRepository;
        this.producerRepository = producerRepository;
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void processInputFileAtStart() {

        List<MovieModel> records = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        //try (Scanner scanner = new Scanner(new File(Objects.requireNonNull(classLoader.getResource("csv/movielist.csv")).getFile()))) {
        try (Scanner scanner = new Scanner(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("csv/movielist.csv")))) {

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                records.add(this.getRecordFromLine(scanner.nextLine()));
            }

            this.persistData(records);
        }
    }

    private MovieModel getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(";");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }

        return processModelFromCsv.createModel(values, pairMappingStudios, pairMappingProducers);
    }

    private void persistData(List<MovieModel> records) {

        this.pairMappingStudios.forEach(studioItem -> studioRepository.save(studioItem.getSecond()));
        this.pairMappingProducers.forEach(producerItem -> producerRepository.save(producerItem.getSecond()));
        this.movieRepository.saveAll(records);
    }
}
