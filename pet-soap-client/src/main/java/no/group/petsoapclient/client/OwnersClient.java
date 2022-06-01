package no.group.petsoapclient.client;

import no.group.petsoapclient.wsdl.GetOwnersRequest;
import no.group.petsoapclient.wsdl.GetOwnersResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class OwnersClient extends WebServiceGatewaySupport {

    private static final String SERVICE_URI = "http://localhost:8080/ws/owners";
    private static final String NAMESPACE_URI = "http://no.group.petclinic/owners";

    public GetOwnersResponse getOwners() {
        GetOwnersRequest request = new GetOwnersRequest();
        GetOwnersResponse response = (GetOwnersResponse) getWebServiceTemplate()
                .marshalSendAndReceive(SERVICE_URI,request,
                        new SoapActionCallback(NAMESPACE_URI+"/GetOwnersRequest"));
        return response;
    }

}
