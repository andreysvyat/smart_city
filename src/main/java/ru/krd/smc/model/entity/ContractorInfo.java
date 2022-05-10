package ru.krd.smc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ru.krd.smc.model.entity.embeded.OKVED;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "contractor")
@NoArgsConstructor
public class ContractorInfo {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	private String area;
	private String fullName;
	private long inn;

	@Embedded
	@ElementCollection(fetch = EAGER, targetClass = OKVED.class)
	@CollectionTable(name = "okved", joinColumns = @JoinColumn(name = "contractor_id"))
	private List<OKVED> okveds;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
}
