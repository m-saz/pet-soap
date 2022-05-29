package no.group.petclinic.mapper;

import no.group.petclinic.domain.Owner;
import no.group.petclinic.entity.OwnerEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OwnerMapper {
    Owner ownerEntityToDomain(OwnerEntity owner);
    OwnerEntity ownerDomainToEntity(Owner owner);
}
