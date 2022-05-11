package ru.krd.smc.dba;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.krd.smc.model.entity.CityCaseType;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityCaseTypeRepo extends CrudRepository<CityCaseType, UUID> {
	Optional<CityCaseType> findOneByCode(String code);
}
