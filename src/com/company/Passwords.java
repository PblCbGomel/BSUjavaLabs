package com.company;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Passwords {
  String fileName;

  public Passwords(String fileName_) {
    fileName = fileName_;
  }

  public String getMd5Hash(String source) {
    try {
      var md = MessageDigest.getInstance("MD5");
      md.update(source.getBytes());
      byte[] digest = md.digest();
      return bytesToHex(digest);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  private String bytesToHex(byte[] bytes) {
    var builder = new StringBuilder();
    for (var b : bytes) {
      builder.append(String.format("%02x", b & 0xff));
    }
    return builder.toString();
  }

  public ArrayList<String> getAllPasswords() {
    ArrayList<String> result = new ArrayList<>();
    try {
      FileReader fr = new FileReader(fileName);
      Scanner read = new Scanner(fr);
      while(read.hasNextLine()) {
        result.add(read.nextLine());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return result;
  }

  public void addPassword(String str) {
    String pass = getMd5Hash(str);
    try {
      FileWriter writer = new FileWriter(fileName, true);
      BufferedWriter bufferWriter = new BufferedWriter(writer);
      bufferWriter.write(pass + "\n");
      bufferWriter.close();
    }
    catch (IOException e) {
      System.out.println(e);
    }
  }
}
