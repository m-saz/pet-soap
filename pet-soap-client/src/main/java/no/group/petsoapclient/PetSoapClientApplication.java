package no.group.petsoapclient;

import no.group.petsoapclient.client.OwnersClient;
import no.group.petsoapclient.wsdl.GetOwnersResponse;
import no.group.petsoapclient.wsdl.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PetSoapClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(PetSoapClientApplication.class, args);
	}
}
