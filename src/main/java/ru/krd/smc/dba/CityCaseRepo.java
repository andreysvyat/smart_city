package ru.krd.smc.dba;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.krd.smc.model.entity.CityCase;
import ru.krd.smc.model.entity.User;
import ru.krd.smc.model.enums.CityCaseStatus;
import ru.krd.smc.model.entity.CityCaseType;

import java.time.ZonedDateTime;
import java.util.UUID;

@Repository
public interface CityCaseRepo extends CrudRepository<CityCase, UUID> {
	Page<CityCase> findAll(Pageable pageable);
	Page<CityCase> findAllByAuthor(User author, Pageable pageable);
	Page<CityCase> findAllByTypeAndStatus(CityCaseType type, CityCaseStatus status, Pageable pageable);
	Long countByLocationAndInitedOn(String location, ZonedDateTime initedOn);
}
