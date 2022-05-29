package no.group.petclinic.endpoint;

import lombok.RequiredArgsConstructor;
import no.group.petclinic.domain.*;
import no.group.petclinic.service.OwnerService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;

@Endpoint
@RequiredArgsConstructor
public class OwnerEndpoint {

    private static final String NAMESPACE_URI = "http://no.group.petclinic/owners";

    private final OwnerService ownerService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOwnersRequest")
    @ResponsePayload
    public GetOwnersResponse getOwners(@RequestPayload GetOwnersRequest request) {
        String keyword = request.getKeyword();
        if(keyword != null) {
            return ownerService.searchOwners(keyword);
        }
        else {
            return ownerService.getOwners();
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSingleOwnerRequest")
    @ResponsePayload
    public GetSingleOwnerResponse getOwner(@RequestPayload GetSingleOwnerRequest request){
        Integer id = request.getId().intValue();
        return ownerService.getOwner(id);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveOwnerRequest")
    @ResponsePayload
    public SaveOwnerResponse saveOwner(@RequestPayload SaveOwnerRequest request){
        Owner owner = request.getOwner();
        return ownerService.saveOwner(owner);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateOwnerRequest")
    @ResponsePayload
    public  UpdateOwnerResponse updateOwner(@RequestPayload UpdateOwnerRequest request){
        Owner owner = request.getOwner();
        return ownerService.updateOwner(owner);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteOwnerRequest")
    @ResponsePayload
    public  DeleteOwnerResponse deleteOwner(@RequestPayload DeleteOwnerRequest request){
        Integer id = request.getId().intValue();
        return  ownerService.deleteOwner(id);
    }

}
