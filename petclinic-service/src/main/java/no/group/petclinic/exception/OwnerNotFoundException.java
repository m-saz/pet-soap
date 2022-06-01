package no.group.petclinic.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class OwnerNotFoundException extends RuntimeException {
	public OwnerNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
