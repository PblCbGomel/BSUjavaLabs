package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileWork {
  public static ArrayList<String> readPasswordsInArrayList (String fileName) {
    BufferedReader fileIn = null;
    ArrayList<String> passwords = new ArrayList<>();
    try {
      fileIn = new BufferedReader(
          new InputStreamReader(
              new FileInputStream(fileName), StandardCharsets.UTF_8));
      System.out.println("File " + fileName + " was read.");

      String pass = new String();
      while ((pass = fileIn.readLine()) != null) {
        passwords.add(pass);
      }
      fileIn.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return passwords;
  }

  public static void writeStrongPasswordsInFile (ArrayList<String> strongPasswords) {
    try {
      BufferedWriter fileOutStrong = new BufferedWriter(
          new OutputStreamWriter(
              new FileOutputStream("StrongPasswords.txt"), StandardCharsets.UTF_8));

      for(String p : strongPasswords) {
        fileOutStrong.write(p + "\n");
      }

      fileOutStrong.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("File \"StrongPasswords.txt\" was created.");
  }

  public static void writeBadPasswordsInFile (ArrayList<String> badPasswords) {
    try {
      BufferedWriter fileOutBad = new BufferedWriter(
          new OutputStreamWriter(
              new FileOutputStream("BadPasswords.txt"), StandardCharsets.UTF_8));

      for(String p : badPasswords) {
        fileOutBad.write(p + "\n");
      }

      fileOutBad.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("File \"BadPasswords.txt\" was created.");
  }
}
