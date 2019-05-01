package com.cd.rest.repository;

import com.cd.rest.entitymodel.BookTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTypeRepository extends JpaRepository<BookTypeEntity, Integer> {
}
