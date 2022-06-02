package no.group.petsoapclient.model;

import no.group.petsoapclient.wsdl.Owner;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OwnerModelMapper {
    Owner ownerModelToDomain(OwnerModel owner);
    OwnerModel ownerDomainToModel(Owner owner);
    List<OwnerModel> ownerListDomainToModel(List<Owner> owners);
}
