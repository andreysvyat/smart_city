package ru.krd.smc.dba;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.krd.smc.model.entity.ContractorInfo;
import ru.krd.smc.model.entity.OKVED;
import ru.krd.smc.model.entity.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContractorRepo extends CrudRepository<ContractorInfo, UUID> {
	Optional<ContractorInfo> findByUser(User user);

	@Query("Select cntr from ContractorInfo cntr left join cntr.okveds okvs where (:okved) in okvs")
	Optional<ContractorInfo> findByOkved(@Param("okved") OKVED okved);
}
