package no.group.petclinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="pets")
@Getter
@Setter
@ToString
public class PetEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Column(name="birth_date")
	private Date birthDate;
	
	@ManyToOne
	@JoinColumn(name="type_id")
	private TypeEntity type;
	
	@ManyToOne
	@JoinColumn(name="owner_id")
	@ToString.Exclude
	@JsonIgnore
	private OwnerEntity owner;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
				mappedBy = "pet", orphanRemoval = true)
	private List<VisitEntity> visits;
	
}
