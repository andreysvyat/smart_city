package ru.krd.smc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "contractor")
@NoArgsConstructor
public class ContractorInfo implements Serializable {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	private String area;
	private String fullName;
	private long inn;

	@ManyToMany(fetch = EAGER)
	@JoinTable(name = "contractor_okveds",
			joinColumns = @JoinColumn(name = "contractor_id"),
			inverseJoinColumns = @JoinColumn(name = "okved_id")
	)
	private List<OKVED> okveds;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
}
