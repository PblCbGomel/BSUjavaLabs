package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Main {

  public static void main(String[] args) {

    try {
      Scanner in = new Scanner(System.in);
      System.out.print("Input text file name: ");
      String fileName = new String(in.nextLine());

      ArrayList<String> passwords = new ArrayList<>(FileWork.readPasswordsInArrayList(fileName));
      ArrayList<String> badPasswords = new ArrayList<>();
      ArrayList<String> strongPasswords = new ArrayList<>();

      Pattern checkPass = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\w]{8,}$");

      for (String p : passwords) {
        if (Pattern.matches(String.valueOf(checkPass), p)) {
          strongPasswords.add(p);
        } else {
          badPasswords.add(p);
        }
      }

      FileWork.writeStrongPasswordsInFile(strongPasswords);
      FileWork.writeBadPasswordsInFile(badPasswords);

    } catch (PatternSyntaxException e) {
      System.out.println(e);
    } finally {
      System.out.println("Program completed.");
    }
  }
}
