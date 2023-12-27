package com.texo.apitest.repository;

import com.texo.apitest.model.ProducerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<ProducerModel, Long> {
}
