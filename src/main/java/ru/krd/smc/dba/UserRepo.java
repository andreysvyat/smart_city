package ru.krd.smc.dba;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.krd.smc.model.entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, String> {
}
