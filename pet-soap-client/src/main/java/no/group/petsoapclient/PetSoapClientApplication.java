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

	@Autowired
	OwnersClient client;

	public static void main(String[] args) {
		SpringApplication.run(PetSoapClientApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				GetOwnersResponse response = client.getOwners();
				List<Owner> owners = response.getOwners();
				owners.forEach(owner -> System.out.println(owner.getFirstName()));
			}
		};
	}

}
