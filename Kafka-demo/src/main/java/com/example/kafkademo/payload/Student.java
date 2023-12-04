package com.example.kafkademo.payload;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {


    private int id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
