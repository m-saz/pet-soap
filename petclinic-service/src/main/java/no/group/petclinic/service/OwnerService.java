package no.group.petclinic.service;

import petclinic.group.no.owners.*;

public interface OwnerService {

	GetOwnersResponse getOwners();

	GetOwnersResponse searchOwners(String keyword);

	GetSingleOwnerResponse getOwner(Integer ownerId);

	SaveOwnerResponse saveOwner(Owner owner);

	UpdateOwnerResponse updateOwner(Owner owner);

	DeleteOwnerResponse deleteOwner(Integer ownerId);

}
