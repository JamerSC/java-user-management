package org.example.model;

public class Person {
    private String firstName;
    private String lastName;
    private char gender;
    private int age;

    // no argument constructor
    public Person() {
    }

    // constructor with arguments
    public Person(String firstName, String lastName, char gender, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    // getters & setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Custom helper functions

    public void greet() {
        System.out.println("Hello, " + firstName + " " + lastName + "! Welcome to the program.");
    }

    // function to get full name of the person
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // function to check if the person is eligible to vote
    public String isVoter(int age) {
        if (age >= 18) {
            return "You are eligible to vote";
        } else {
            return "You are not eligible to vote";
        }
    }

    // toString() method returns the string itself
    // method may seem redundant, but its purpose is to allow code that is treating the string as a more generalized object to know its string value without casting it to String type
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
