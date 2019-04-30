package com.cd.rest.repository;

import com.cd.rest.entitymodel.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypesRepository extends JpaRepository<TypeEntity, Integer> {
}
