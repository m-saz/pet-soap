package no.group.petsoapclient.config;

import lombok.RequiredArgsConstructor;
import no.group.petsoapclient.model.TypeModel;
import no.group.petsoapclient.repository.TypeHardcodedRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StringToTypeConverter implements Converter<String, TypeModel> {

    private final TypeHardcodedRepository typeRepo;

    @Override
    public TypeModel convert(String source) {
        BigInteger typeId = new BigInteger(source);
        List<TypeModel> types = typeRepo.getTypes();
        return types.stream().filter(type -> type.getId().equals(typeId)).findFirst().get();
    }
}
