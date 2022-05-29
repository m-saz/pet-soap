package no.group.petclinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="visits")
@ToString
public class VisitEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="visit_date")
	Date visitDate;
	
	String description;
	
	@ManyToOne
	@JoinColumn(name="pet_id")
	@ToString.Exclude
	@JsonIgnore
    PetEntity pet;
}
