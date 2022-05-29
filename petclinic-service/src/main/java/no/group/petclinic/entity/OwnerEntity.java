package no.group.petclinic.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "owners")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OwnerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	private String city;
	
	private String address;
	
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY,
				cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PetEntity> pets;
}
