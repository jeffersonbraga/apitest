package com.texo.apitest.services;

import com.texo.apitest.dto.AwardsDTO;
import com.texo.apitest.dto.SearchAwardsIntervalDTO;
import com.texo.apitest.model.ProducerAwardModel;
import com.texo.apitest.repository.ProducerAwardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProducerAwardService {

    private final ProducerAwardRepository producerAwardRepository;

    public ProducerAwardService(ProducerAwardRepository producerAwardRepository) {
        this.producerAwardRepository = producerAwardRepository;
    }

    public SearchAwardsIntervalDTO awardIntervalMaxMin() {

        List<List<ProducerAwardModel>> pa = this.producerAwardRepository.findAll()
                .stream().collect(Collectors.groupingBy(ProducerAwardModel::getProducerId))
                .values()
                .stream()
                .filter(p -> p.size() > 1)
                .toList();

        return this.processMaxMinInterval(this.processGroupedProducers(pa));
    }

    private List<AwardsDTO> processGroupedProducers(List<List<ProducerAwardModel>> groupedAwards) {

        List<AwardsDTO> listInterval = new ArrayList<>();
        groupedAwards.forEach(itGroupedAwardsByProducer -> {
            AwardsDTO awardDTO = new AwardsDTO();
            awardDTO.setProducer(itGroupedAwardsByProducer.get(0).getProducerName());
            itGroupedAwardsByProducer.forEach(itAwardProducer ->
                this.checkProducerAwards(awardDTO, itAwardProducer, listInterval)
            );
        });

        return listInterval;
    }

    private SearchAwardsIntervalDTO processMaxMinInterval(List<AwardsDTO> awardsDTOS) {

        awardsDTOS = awardsDTOS.stream().sorted(Comparator.comparing(AwardsDTO::getInterval)).toList();
        Integer minInterval = awardsDTOS.get(0).getInterval();
        Integer maxInterval = awardsDTOS.get(awardsDTOS.size() - 1).getInterval();
        SearchAwardsIntervalDTO result = new SearchAwardsIntervalDTO();
        awardsDTOS.forEach(f -> {
            if (Objects.equals(f.getInterval(), maxInterval)) {
                result.getMax().add(f);
            } else if (Objects.equals(f.getInterval(), minInterval)) {
                result.getMin().add(f);
            }
        });

        return result;
    }

    private void checkProducerAwards(AwardsDTO awardDTO, ProducerAwardModel itAwardProducer, List<AwardsDTO> listAwards) {

        if (awardDTO.getPreviousWin() == null) {
            awardDTO.setPreviousWin(itAwardProducer.getMovie_year());
        } else {
            if (awardDTO.getFollowingWin() == null) {
                awardDTO.setFollowingWin(itAwardProducer.getMovie_year());
            } else {
                awardDTO.setPreviousWin(awardDTO.getFollowingWin());
                awardDTO.setFollowingWin(itAwardProducer.getMovie_year());
            }

            awardDTO.setInterval(awardDTO.getFollowingWin() - awardDTO.getPreviousWin());

            try {
                listAwards.add(awardDTO.clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
