package ru.krd.smc.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Table(name = "city_case")
public class CityCase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;
    private String location;

    @ElementCollection(fetch = EAGER)
    @CollectionTable(name="files_links", joinColumns = @JoinColumn(name="files_links"))
    private List<String> files;

    @ManyToOne
    @JoinColumn(name = "uuid")
    private User author;
}
