package org.mouthaan.examples.mapper;

import org.junit.jupiter.api.Test;
import org.mouthaan.examples.dto.PersonDto;
import org.mouthaan.examples.entity.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PersonMapperTest {

    private PersonMapper personMapper = PersonMapper.MAPPER;

    @Test
    void MapPersonToPersonDtoTest() {
        // given
        Person p = new Person();
        p.setName("Raymond");
        p.setAge(40);
        List<String> friends = new ArrayList<>();
        friends.add("John");
        friends.add("James");
        friends.add("Michael");
        p.setFriends(friends);
        Map<String, Integer> grades = new HashMap<>();
        grades.put("English", 7);
        grades.put("German", 6);
        grades.put("Dutch", 9);
        p.setGrades(grades);

        // when
        PersonDto pDto = personMapper.toPersonDto(p);

        // then
        assertNotNull(pDto);
        assertEquals(p.getName(), pDto.getFirstName());
        assertEquals(p.getAge(), pDto.getAge());
        assertEquals(p.getFriends(), pDto.getFriends());
        assertEquals(p.getGrades(), pDto.getGrades());
    }

    @Test
    void MapPersonDtoToPersonTest() {
        // given
        PersonDto pDto = new PersonDto();
        pDto.setFirstName("Raymond");
        pDto.setAge(40);

        List<String> friends = new ArrayList<>();
        friends.add("John");
        friends.add("James");
        friends.add("Michael");
        pDto.setFriends(friends);
        Map<String, Integer> grades = new HashMap<>();
        grades.put("English", 7);
        grades.put("German", 6);
        grades.put("Dutch", 9);
        pDto.setGrades(grades);

        // when
        Person p = personMapper.toPerson(pDto);

        // then
        assertNotNull(p);
        assertEquals(pDto.getFirstName(), p.getName());
        assertEquals(pDto.getAge(), p.getAge());
        assertEquals(pDto.getFriends(), p.getFriends());
        assertEquals(pDto.getGrades(), p.getGrades());
    }

    @Test
    void MergePersonTest() {
        // given
        Person p = new Person();
        p.setName("Raymond Marcel");
        p.setAge(41);

        List<String> friends = new ArrayList<>();
        friends.add("John");
        friends.add("James");
        friends.add("Michael");
        p.setFriends(friends);
        Map<String, Integer> grades = new HashMap<>();
        grades.put("English", 7);
        grades.put("German", 6);
        grades.put("Dutch", 9);
        p.setGrades(grades);

        PersonDto pDto = new PersonDto();
        pDto.setAge(40);
        Map<String, Integer> newGrades = new HashMap<>();
        newGrades.put("English", 9);
        newGrades.put("German", 5);
        newGrades.put("Dutch", 8);
        pDto.setGrades(newGrades);

        // when
        personMapper.mergePersonDto(pDto, p);

        // then
        assertNotEquals(p.getName(), pDto.getFirstName());
        assertEquals(p.getAge(), pDto.getAge());
        assertEquals(p.getFriends(), friends);
        assertEquals(p.getGrades(), pDto.getGrades());
    }

    @Test
    void MergePersonDtoTest() {
        // given
        PersonDto pDto = new PersonDto();
        pDto.setFirstName("Raymond Marcel");
        pDto.setAge(41);

        List<String> friends = new ArrayList<>();
        friends.add("John");
        friends.add("James");
        friends.add("Michael");
        pDto.setFriends(friends);

        Person p = new Person();
        p.setAge(40);

        // when
        personMapper.mergePerson(p, pDto);

        // then
        assertNotEquals(pDto.getFirstName(), p.getName());
        assertEquals(pDto.getAge(), p.getAge());
        assertEquals(pDto.getFriends(), friends);
    }
}