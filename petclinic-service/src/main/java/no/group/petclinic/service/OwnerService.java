package no.group.petclinic.service;

import no.group.petclinic.domain.*;
import no.group.petclinic.entity.OwnerEntity;
import org.springframework.data.domain.Pageable;

public interface OwnerService {

	public GetOwnersResponse getOwners();

	public GetOwnersResponse searchOwners(String keyword);

	public GetSingleOwnerResponse getOwner(Integer ownerId);

	public SaveOwnerResponse saveOwner(Owner owner);

	public UpdateOwnerResponse updateOwner(Owner owner);

	public DeleteOwnerResponse deleteOwner(Integer ownerId);

}
