package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class TelephoneBook {

  Hashtable<String, Person> telephoneBook = new Hashtable();
  Hashtable<String, Person> lastNameBook = new Hashtable();
  Hashtable<String, Person> addressBook = new Hashtable();
  Hashtable<String, Person> sortedBook = new Hashtable<>();

  TelephoneBook(String fileName) {
    try {
      FileInputStream fileInput = new FileInputStream(fileName);
      ObjectInputStream readObj = new ObjectInputStream(fileInput);
      Person p;
      while (fileInput.available() > 0) {
        p = new Person((Person) readObj.readObject());
        if (p == null) {
          break;
        }
        telephoneBook.put(p.getNumber(), p);
        lastNameBook.put(p.getLastName(), p);
        addressBook.put(p.getAddress(), p);
        sortedBook.put(p.number, p);
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  void outputSortedTB() {
    ArrayList<String> tmp = Collections.list(sortedBook.keys());
    Collections.sort(tmp);
    Iterator<String> it = tmp.iterator();

    while (it.hasNext()) {
      Person p = sortedBook.get(it.next());
      System.out.println(p + " " + p.hashCode());
    }

  }

  Person getPersonByTelephone(String number) {
    return telephoneBook.get(number);
  }

  Person getPersonByLastName(String lastName) {
    return lastNameBook.get(lastName);
  }

  Person getPersonByAddress(String address) {
    return addressBook.get(address);
  }

  void outputAllBook() {
    Collections.list(telephoneBook.keys())
        .forEach((elem) -> System.out.println(telephoneBook.get(elem) + " "
            + telephoneBook.get(elem).hashCode()));
  }

  @Override
  public int hashCode() {
    return telephoneBook.hashCode();
  }
}
