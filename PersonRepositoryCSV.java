package com.example.baitapbuoi3.Service;

import com.example.baitapbuoi3.Model.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PersonRepositoryCSV implements PersonRepositoryInterface{
    private final String csvFilePath;

    public PersonRepositoryCSV(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public List<Person> inSalaryRange(int start, int end) {
        return readDataFromCSV().stream()
                .filter(person -> person.getSalary() >= start && person.getSalary() <= end)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> startsWith(String prefix) {
        return readDataFromCSV().stream()
                .filter(person -> person.getName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> sortByBirthYearDescending() {
        return readDataFromCSV().stream()
                .sorted(Comparator.comparingInt(Person::getBirthYear).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> top5HighestPaid() {
        return readDataFromCSV().stream()
                .sorted(Comparator.comparingDouble(Person::getSalary).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> inAgeRange(int start, int end) {
        return readDataFromCSV().stream()
                .filter(person -> person.getAge() >= start && person.getAge() <= end)
                .collect(Collectors.toList());
    }

    private List<Person> readDataFromCSV() {
        List<Person> persons = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                String name = nextRecord[0];
                int age = Integer.parseInt(nextRecord[1]);
                double salary = Double.parseDouble(nextRecord[2]);
                int birthYear = Integer.parseInt(nextRecord[3]);

                Person person = new Person(name, age, salary, birthYear);
                persons.add(person);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Xử lý các exception theo ý muốn của bạn
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return persons;
    }
}
