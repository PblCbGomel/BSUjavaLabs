package com.company;

import java.io.Serializable;

public class Person implements Serializable {

  String name;
  String lastName;
  String address;
  String number;

  Person() {
    name = "VoidName";
    lastName = "VoidLastName";
    address = "VoidAddress";
    number = "+375000000000";
  }

  Person(String name_, String lastName_, String address_, String number_) {
    name = name_;
    lastName = lastName_;
    address = address_;
    number = number_;
  }

  Person(Person copy) {
    name = copy.name;
    lastName = copy.lastName;
    address = copy.address;
    number = copy.number;
  }

  String getName() {
    return name;
  }

  String getLastName() {
    return lastName;
  }

  String getAddress() {
    return address;
  }

  String getNumber() {
    return number;
  }

  @Override
  public String toString() {
    return name + " " + lastName + " " + address + " " + number;
  }

  @Override
  public boolean equals(Object person) {
    return person instanceof Person
        && name.equals(((Person) person).name)
        && lastName.equals(((Person) person).lastName)
        && address.equals(((Person) person).address)
        && number.equals(((Person) person).number);
  }

  @Override
  public int hashCode() {
    return Integer.parseInt(number.substring(4, number.length() - 1));
  }
}
