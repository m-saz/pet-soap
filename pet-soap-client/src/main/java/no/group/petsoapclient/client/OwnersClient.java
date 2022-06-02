package no.group.petsoapclient.client;

import lombok.RequiredArgsConstructor;
import no.group.petsoapclient.model.OwnerModel;
import no.group.petsoapclient.model.OwnerModelMapper;
import no.group.petsoapclient.wsdl.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OwnersClient extends WebServiceGatewaySupport {

    private static final String SERVICE_URI = "http://localhost:8080/ws/owners";
    private static final String NAMESPACE_URI = "http://no.group.petclinic/owners";

    private final OwnerModelMapper ownerMapper;
    private final Jaxb2Marshaller marshaller;

    @PostConstruct
    public void init(){
        this.setDefaultUri("http://localhost:8080/ws");
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public List<OwnerModel> getOwnersResponse() {
        return getOwnersResponse(null);
    }

    public List<OwnerModel> getOwnersResponse(String keyword) {
        GetOwnersRequest request = new GetOwnersRequest();
        request.setKeyword(keyword);
        GetOwnersResponse response = (GetOwnersResponse) getWebServiceTemplate()
                .marshalSendAndReceive(SERVICE_URI,request,
                        new SoapActionCallback(NAMESPACE_URI+"/GetOwnersRequest"));
        List<OwnerModel> owners = ownerMapper.ownerListDomainToModel(response.getOwners());
        return owners;
    }

    public OwnerModel getSingleOwnerResponse(Integer ownerId) {
        GetSingleOwnerRequest request = new GetSingleOwnerRequest();
        request.setId(BigInteger.valueOf(ownerId));
        GetSingleOwnerResponse response = (GetSingleOwnerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(SERVICE_URI, request,
                        new SoapActionCallback(NAMESPACE_URI+"/GetSingleOwnerRequest"));
        OwnerModel owner = ownerMapper.ownerDomainToModel(response.getOwner());
        return owner;
    }

    public SaveOwnerResponse saveOwnerResponse(OwnerModel owner) {
        Owner output = ownerMapper.ownerModelToDomain(owner);
        SaveOwnerRequest request = new SaveOwnerRequest();
        request.setOwner(output);
        SaveOwnerResponse response = (SaveOwnerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(SERVICE_URI, request,
                        new SoapActionCallback(NAMESPACE_URI+"/SaveOwnerRequest"));
        return response;
    }

    public UpdateOwnerResponse updateOwnerResponse(OwnerModel owner) {
        Owner output = ownerMapper.ownerModelToDomain(owner);
        UpdateOwnerRequest request = new UpdateOwnerRequest();
        request.setOwner(output);
        UpdateOwnerResponse response = (UpdateOwnerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(SERVICE_URI, request,
                        new SoapActionCallback(NAMESPACE_URI+"/UpdateOwnerRequest"));
        return response;
    }

    public DeleteOwnerResponse deleteOwnerResponse(Integer ownerId) {
        DeleteOwnerRequest request = new DeleteOwnerRequest();
        request.setId(BigInteger.valueOf(ownerId));
        DeleteOwnerResponse response = (DeleteOwnerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(SERVICE_URI, request,
                        new SoapActionCallback(NAMESPACE_URI+"/DeleteOwnerRequest"));
        return response;
    }
}
