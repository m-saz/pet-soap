package no.group.petsoapclient.config;

import no.group.petsoapclient.client.OwnersClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("no.group.petsoapclient.wsdl");
        return marshaller;
    }

    @Bean
    public OwnersClient ownersClient(Jaxb2Marshaller marshaller) {
        OwnersClient client = new OwnersClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
