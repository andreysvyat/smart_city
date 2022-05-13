package ru.krd.smc.dba;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.krd.smc.model.entity.CityCase;
import ru.krd.smc.model.entity.User;
import ru.krd.smc.model.enums.CityCaseStatus;
import ru.krd.smc.model.entity.CityCaseType;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityCaseRepo extends CrudRepository<CityCase, UUID> {
	Page<CityCase> findAll(Pageable pageable);
	Page<CityCase> findAllByAuthor(User author, Pageable pageable);
	List<CityCase> findAllByAuthor(User author);
	Page<CityCase> findAllByTypeAndStatus(CityCaseType type, CityCaseStatus status, Pageable pageable);
	@Query("select city_case from CityCase city_case where city_case.id != ?1 and city_case.location = ?2 and initedOn = ?3 and status != ?4")
	Optional<CityCase> findDuplicates(UUID id, String location, ZonedDateTime initedOn, CityCaseStatus status);
}
