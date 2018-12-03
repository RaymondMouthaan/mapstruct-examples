package org.mouthaan.examples.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PersonDto {
    private String firstName;
    private Integer age;
    private List friends;
    private Map<String, Integer> grades;
}
