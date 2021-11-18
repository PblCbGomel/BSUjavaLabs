package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

  public static void main(String[] args) {
    String fileName = "input.txt";
    /*Person p1 = new Person("Jenya,", "Shevtsov",
        "Gomel_Berestyanskaya_15", "+375299991234");
    Person p2 = new Person("Andrey", "Golovatskiy",
        "Minsk_Nezalejnasti_69", "+375442341144");
    Person p3 = new Person("Veronika", "Vasilevich",
        "Brest_Nezalehnasti_50", "+375257854312");
    Person p4 = new Person("Misha", "Ken",
        "Minsk_Nezalehnasti_56", "+375257654111");
    Person p5 = new Person("Nadya", "Ivanova",
        "Minsk_ImeniBratievLizukovih_23", "+375291857633");
    Person p6 = new Person("Veronika", "Kastrica",
        "Minsk_Natopirkina_124", "+375449904687");
    Person p7 = new Person("Oleg", "Petrovich",
        "Gomel_Malugina_23", "+375445455544");
    Person p8 = new Person("Vitya", "Nikolaev",
        "Gomel_Sovetskaya_134", "+375299854331");
    Person p9 = new Person("Alexandr", "Filipovich",
        "Vitebsk_Pavlova_1", "+375291234567");
    Person p10 = new Person("Sergey", "Maloy",
        "Grodno_JakubaKolasa_3", "+375254494343");
    try {
      ObjectOutputStream writeObj = new ObjectOutputStream(new FileOutputStream(fileName));
      writeObj.writeObject(p1);
      writeObj.writeObject(p2);
      writeObj.writeObject(p3);
      writeObj.writeObject(p4);
      writeObj.writeObject(p5);
      writeObj.writeObject(p6);
      writeObj.writeObject(p7);
      writeObj.writeObject(p8);
      writeObj.writeObject(p9);
      writeObj.writeObject(p10);
      writeObj.close();
    } catch (IOException e) {
      e.printStackTrace();
    }*/

    TelephoneBook tb = new TelephoneBook(fileName);
    Person p = tb.getPersonByTelephone("+375254494343");
    System.out.println(p);
    System.out.println(tb.getPersonByAddress("Brest_Nezalehnasti_50"));
    System.out.println(tb.getPersonByLastName("Ivanova"));
    System.out.println("===================Sorted table===================");
    tb.outputSortedTB();
    System.out.println("===================Without sort===================");
    tb.outputAllBook();
  }
}

