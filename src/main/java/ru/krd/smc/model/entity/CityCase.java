package ru.krd.smc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ru.krd.smc.model.enums.CityCaseStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;

import static javax.persistence.EnumType.STRING;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "city_case")
@NoArgsConstructor
public class CityCase {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	private String location;
	private String description;
	private String address;
	private ZonedDateTime initedOn;

	@Enumerated(STRING)
	private CityCaseStatus status;

	@ManyToOne
	@JoinColumn(name = "type", referencedColumnName = "code")
	private CityCaseType type;

	@ElementCollection
	@CollectionTable(name = "files", joinColumns = @JoinColumn(name = "city_case_id"))
	@Column(name = "file_link")
	private List<String> files;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;
}
