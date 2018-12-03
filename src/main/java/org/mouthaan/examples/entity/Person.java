package org.mouthaan.examples.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Person {
    private String name;
    private Integer age;
    private List<String> friends;
    private Map<String, Integer> grades;
}
