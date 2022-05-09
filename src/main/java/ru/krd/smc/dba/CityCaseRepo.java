package ru.krd.smc.dba;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.krd.smc.model.entity.CityCase;

import java.util.UUID;

@Repository
public interface CityCaseRepo extends CrudRepository<CityCase, UUID> {
	Page<CityCase> findAll(Pageable pageable);
}
