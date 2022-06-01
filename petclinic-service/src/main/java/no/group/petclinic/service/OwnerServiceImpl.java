package no.group.petclinic.service;

import lombok.RequiredArgsConstructor;
import no.group.petclinic.domain.*;
import no.group.petclinic.entity.OwnerEntity;
import no.group.petclinic.entity.PetEntity;
import no.group.petclinic.entity.VisitEntity;
import no.group.petclinic.exception.OwnerNotFoundException;
import no.group.petclinic.mapper.OwnerMapper;
import no.group.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Override
    @Transactional
    public GetOwnersResponse getOwners() {
        GetOwnersResponse response = new GetOwnersResponse();
        List<OwnerEntity> owners = ownerRepository.findAll();

        owners.forEach(owner -> {
            response.getOwners().add(
                    ownerMapper.ownerEntityToDomain(owner)
            );
        });
        return response;
    }

    @Override
    @Transactional
    public GetOwnersResponse searchOwners(String keyword) {
        GetOwnersResponse response = new GetOwnersResponse();
        List<OwnerEntity> owners =
                ownerRepository
                        .findByFirstNameContainingOrLastNameContainingAllIgnoreCase(
                                keyword, keyword);
        owners.forEach(owner -> {
            response.getOwners().add(
                    ownerMapper.ownerEntityToDomain(owner)
            );
        });
        return response;
    }

    @Override
    @Transactional
    public GetSingleOwnerResponse getOwner(Integer ownerId) {
        GetSingleOwnerResponse response = new GetSingleOwnerResponse();
        OwnerEntity owner = ownerRepository.findById(ownerId).orElseThrow(() ->
                new OwnerNotFoundException("Can't find Owner with id: " + ownerId));

        response.setOwner(ownerMapper.ownerEntityToDomain(owner));

        return response;
    }

    @Override
    @Transactional
    public SaveOwnerResponse saveOwner(Owner owner) {

        SaveOwnerResponse response = new SaveOwnerResponse();
        response.setStatus(OperationStatus.OK);
        OwnerEntity ownerEntity = ownerMapper.ownerDomainToEntity(owner);
        if (ownerEntity != null) {
            setForeignKeys(ownerEntity);
            removeIds(ownerEntity);
            ownerRepository.save(ownerEntity);
        }
		else {
			response.setStatus(OperationStatus.ERROR);
		}
        return response;
    }

    @Override
    @Transactional
    public UpdateOwnerResponse updateOwner(Owner owner) {

        UpdateOwnerResponse response = new UpdateOwnerResponse();
        response.setStatus(OperationStatus.OK);

        Integer ownerId = owner.getId().intValue();
        if(ownerRepository.existsById(ownerId)){
            OwnerEntity ownerEntity = ownerMapper.ownerDomainToEntity(owner);
            setForeignKeys(ownerEntity);
            ownerRepository.findById(ownerId);
            ownerRepository.save(ownerEntity);
        } else {
            throw new OwnerNotFoundException("Can't find Owner with id: " + ownerId);
        }
        return response;
    }

    @Override
    public DeleteOwnerResponse deleteOwner(Integer ownerId) {

        DeleteOwnerResponse response = new DeleteOwnerResponse();
        response.setStatus(OperationStatus.OK);
        if(ownerRepository.existsById(ownerId)){
            ownerRepository.deleteById(ownerId);
        }
        else {
            throw new OwnerNotFoundException("Can't find Owner with id: " + ownerId);
        }
        return response;
    }

    private static void setForeignKeys(OwnerEntity owner) {
        for (PetEntity pet : owner.getPets()) {
            if (pet.getVisits() != null) {
                for (VisitEntity visit : pet.getVisits()) {
                    visit.setPet(pet);
                }
            }
            pet.setOwner(owner);
        }
    }

    private void removeIds(OwnerEntity ownerEntity) {
        ownerEntity.setId(null);
        if(ownerEntity.getPets() != null){
            ownerEntity.getPets().forEach(petEntity -> {
                petEntity.setId(null);
                if(petEntity.getVisits() != null){
                    petEntity.getVisits().forEach(visitEntity -> {
                        visitEntity.setId(null);
                    });
                }
            });
        }
    }

}
