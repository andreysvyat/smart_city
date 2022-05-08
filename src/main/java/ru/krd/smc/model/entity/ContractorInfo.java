package ru.krd.smc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

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

	@OneToOne
	@JoinColumn(name = "user_id")
	private User userInfo;
}
