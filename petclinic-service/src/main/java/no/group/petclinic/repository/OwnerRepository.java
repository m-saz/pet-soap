package no.group.petclinic.repository;

import no.group.petclinic.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Integer> {

    List<OwnerEntity> findByFirstNameContainingOrLastNameContainingAllIgnoreCase(
                                        String firstName, String lastName);
}
