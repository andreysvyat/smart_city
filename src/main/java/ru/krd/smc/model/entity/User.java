package ru.krd.smc.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.krd.smc.model.enums.UserType;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Data
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @Enumerated(STRING)
    private UserType type;
}
