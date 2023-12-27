package com.texo.apitest.repository;

import com.texo.apitest.model.StudioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<StudioModel, Long> {
}
