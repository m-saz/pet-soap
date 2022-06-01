package no.group.petclinic.service;

import no.group.petclinic.domain.*;

public interface OwnerService {

	GetOwnersResponse getOwners();

	GetOwnersResponse searchOwners(String keyword);

	GetSingleOwnerResponse getOwner(Integer ownerId);

	SaveOwnerResponse saveOwner(Owner owner);

	UpdateOwnerResponse updateOwner(Owner owner);

	DeleteOwnerResponse deleteOwner(Integer ownerId);

}
