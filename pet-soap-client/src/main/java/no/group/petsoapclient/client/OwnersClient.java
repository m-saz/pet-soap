package no.group.petsoapclient.client;

import no.group.petsoapclient.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.math.BigInteger;

public class OwnersClient extends WebServiceGatewaySupport {

    private static final String SERVICE_URI = "http://localhost:8080/ws/owners";
    private static final String NAMESPACE_URI = "http://no.group.petclinic/owners";

    public GetOwnersResponse getOwnersResponse() {
        return getOwnersResponse(null);
    }

    public GetOwnersResponse getOwnersResponse(String keyword) {
        GetOwnersRequest request = new GetOwnersRequest();
        request.setKeyword(keyword);
        GetOwnersResponse response = (GetOwnersResponse) getWebServiceTemplate()
                .marshalSendAndReceive(SERVICE_URI,request,
                        new SoapActionCallback(NAMESPACE_URI+"/GetOwnersRequest"));
        return response;
    }

    public GetSingleOwnerResponse getSingleOwnerResponse(Integer ownerId) {
        GetSingleOwnerRequest request = new GetSingleOwnerRequest();
        request.setId(BigInteger.valueOf(ownerId));
        GetSingleOwnerResponse response = (GetSingleOwnerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(SERVICE_URI, request,
                        new SoapActionCallback(NAMESPACE_URI+"/GetSingleOwnerRequest"));
        return response;
    }

    public SaveOwnerResponse saveOwnerResponse(Owner owner) {
        SaveOwnerRequest request = new SaveOwnerRequest();
        request.setOwner(owner);
        SaveOwnerResponse response = (SaveOwnerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(SERVICE_URI, request,
                        new SoapActionCallback(NAMESPACE_URI+"/SaveOwnerRequest"));
        return response;
    }

    public UpdateOwnerResponse updateOwnerResponse(Owner owner) {
        UpdateOwnerRequest request = new UpdateOwnerRequest();
        request.setOwner(owner);
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
