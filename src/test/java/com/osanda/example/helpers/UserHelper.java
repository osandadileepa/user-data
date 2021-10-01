package com.osanda.example.helpers;

import com.osanda.example.entity.City;
import com.osanda.example.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserHelper {

    public static User user1 = User.builder()
            .id(67L)
            .firstName("My")
            .lastName("One")
            .mobileNumber("+943334321314")
            .dateOfBirth(LocalDate.parse("1933-12-03", DateTimeFormatter.ISO_DATE))
            .city(new City("New York"))
            .build();

    public static User user2 = User.builder()
            .id(611L)
            .firstName("First")
            .lastName("Last")
            .mobileNumber("+9433323321314")
            .dateOfBirth(LocalDate.parse("1989-12-03", DateTimeFormatter.ISO_DATE))
            .city(new City("Capetown"))
            .build();


    public static User user3 = User.builder()
            .id(62227L)
            .firstName("Osanda")
            .lastName("Wedamulla")
            .mobileNumber("+943377766321314")
            .dateOfBirth(LocalDate.parse("1989-12-03", DateTimeFormatter.ISO_DATE))
            .city(new City("Colombo"))
            .build();
}
