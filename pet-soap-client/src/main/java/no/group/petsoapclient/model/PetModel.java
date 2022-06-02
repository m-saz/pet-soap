package no.group.petsoapclient.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
public class PetModel {
    private BigInteger id;
    private String name;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;
    private TypeModel type;
    private List<VisitModel> visits;
}
