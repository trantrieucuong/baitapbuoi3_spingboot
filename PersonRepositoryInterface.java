package com.example.baitapbuoi3.Service;

import com.example.baitapbuoi3.Model.Person;

import java.util.List;

public interface PersonRepositoryInterface {
    List<Person> inSalaryRange(int start, int end);

    List<Person> startsWith(String prefix);

    List<Person> sortByBirthYearDescending();

    List<Person> top5HighestPaid();

    List<Person> inAgeRange(int start, int end);

}
