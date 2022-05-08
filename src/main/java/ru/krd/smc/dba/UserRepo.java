package ru.krd.smc.dba;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.krd.smc.model.entity.User;

import java.util.UUID;

@Repository
public interface UserRepo extends CrudRepository<User, UUID> {
}
