package com.company;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Passwords p = new Passwords("passwords.txt");

    while (true) {

      System.out.println("Input password: ");
      String password = in.nextLine();

      if (password.toLowerCase().equals("register")) {
        System.out.println("Input new password: ");
        String newPassword = in.nextLine();
        p.addPassword(newPassword);
        continue;
      }

      ArrayList<String> arr = p.getAllPasswords();
      boolean truePassword = false;
      String codeP = p.getMd5Hash(password);

      for (int i = 0; i < arr.size(); ++i) {
        if (arr.get(i).equals(codeP)) {
          truePassword = true;
          break;
        }
      }

      if (truePassword == true) {
        System.out.println("Password is correct.");
        break;
      } else {
        System.out.println("Password isn't correct, try again.");
      }
    }

    DateStorage ds1 = new DateStorage();
    ds1.readFromFile("ships.txt");
    ds1.writeInBinary("ships_bin.bin");

    DateStorage ds2 = new DateStorage();
    ds2.readFromBinary("ships_bin.bin");
    ds2.sort();
    ds2.writeInXML("sortedXML.xml");

    DateStorage ds3 = new DateStorage();
    ds3.readFromBinary("ships_bin.bin");
    ds3.writeXMLWithEncoder("decoder.xml");


  }
}
