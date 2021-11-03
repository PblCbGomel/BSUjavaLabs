package com.company;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Input text file name: ");
    String fileName = new String(in.nextLine());
    TextWork.changeWordsInText(fileName);
  }
}
