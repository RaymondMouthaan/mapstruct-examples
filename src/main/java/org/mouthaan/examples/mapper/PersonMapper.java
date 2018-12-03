package org.mouthaan.examples.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.mouthaan.examples.dto.PersonDto;
import org.mouthaan.examples.entity.Person;

@Mapper
public interface PersonMapper {

    PersonMapper MAPPER = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "firstName", target = "name")
    Person toPerson(PersonDto personDto);

    @InheritInverseConfiguration(name = "toPerson")
    PersonDto toPersonDto(Person person);

    @InheritConfiguration(name = "toPerson")
    @Mapping(ignore = true, target = "name")
    void mergePersonDto(PersonDto personDto, @MappingTarget Person person);

    @InheritConfiguration(name = "toPersonDto")
    @Mapping(ignore = true, target = "firstName")
    void mergePerson(Person person, @MappingTarget PersonDto personDto);
}
