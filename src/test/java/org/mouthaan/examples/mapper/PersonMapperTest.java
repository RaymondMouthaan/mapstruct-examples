package org.mouthaan.examples.mapper;

import org.junit.jupiter.api.Test;
import org.mouthaan.examples.dto.PersonDto;
import org.mouthaan.examples.entity.Person;

import static org.junit.jupiter.api.Assertions.*;

class PersonMapperTest {

    private PersonMapper personMapper = PersonMapper.MAPPER;

    @Test
    void MapPersonToPersonDtoTest() {
        // given
        Person p = new Person();
        p.setName("Raymond");
        p.setAge(40);

        // when
        PersonDto pDto = personMapper.toPersonDto(p);

        // then
        assertNotNull(pDto);
        assertEquals(p.getName(), pDto.getFirstName());
        assertEquals(p.getAge(), pDto.getAge());
    }

    @Test
    void MapPersonDtoToPersonTest() {
        // given
        PersonDto pDto = new PersonDto();
        pDto.setFirstName("Raymond");
        pDto.setAge(40);

        // when
        Person p = personMapper.toPerson(pDto);

        // then
        assertNotNull(p);
        assertEquals(pDto.getFirstName(), p.getName());
        assertEquals(pDto.getAge(), p.getAge());
    }

    @Test
    void MergePersonTest() {
        // given
        Person p = new Person();
        p.setName("Raymond Marcel");
        p.setAge(41);

        PersonDto pDto = new PersonDto();
        pDto.setAge(40);

        // when
        personMapper.mergePersonDto(pDto, p);

        // then
        assertNotEquals(p.getName(), pDto.getFirstName());
        assertEquals(p.getAge(), pDto.getAge());
    }

    @Test
    void MergePersonDtoTest() {
        // given
        PersonDto pDto = new PersonDto();
        pDto.setFirstName("Raymond Marcel");
        pDto.setAge(41);

        Person p = new Person();
        p.setAge(40);

        // when
        personMapper.mergePerson(p, pDto);

        // then
        assertNotEquals(pDto.getFirstName(), p.getName());
        assertEquals(pDto.getAge(), p.getAge());
    }
}