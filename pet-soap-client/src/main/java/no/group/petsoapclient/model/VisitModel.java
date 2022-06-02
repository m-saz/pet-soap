package no.group.petsoapclient.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class VisitModel {
    private BigInteger id;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate visitDate;
    private String description;
}
