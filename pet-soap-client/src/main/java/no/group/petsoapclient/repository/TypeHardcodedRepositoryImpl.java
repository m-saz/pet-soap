package no.group.petsoapclient.repository;

import no.group.petsoapclient.model.TypeModel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class TypeHardcodedRepositoryImpl implements TypeHardcodedRepository {

    List<TypeModel> types;

    @PostConstruct
    private void initTypes(){
        types = new ArrayList<>();
        types.add(createType(BigInteger.valueOf(1), "cat"));
        types.add(createType(BigInteger.valueOf(2), "dog"));
        types.add(createType(BigInteger.valueOf(3), "lizard"));
        types.add(createType(BigInteger.valueOf(4), "snake"));
        types.add(createType(BigInteger.valueOf(5), "bird"));
        types.add(createType(BigInteger.valueOf(6), "hamster"));
    }

    public List<TypeModel> getTypes(){
        return types;
    }

    private static TypeModel createType(BigInteger id, String typeName){
        TypeModel type = new TypeModel();
        type.setId(id);
        type.setType(typeName);
        return type;
    }
}
