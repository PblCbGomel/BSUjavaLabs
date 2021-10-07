package com.company;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    String defaultString = new String();
    String separators = new String();

    Scanner in = new Scanner(System.in);
    System.out.print("Input default string: ");
    defaultString = in.nextLine();

    System.out.print("Input separators: ");
    separators = in.nextLine();

    System.out.println("===========================Result===========================");

    ArrayList<String> num16x = new ArrayList<String>();
    ArrayList<String> notNum16x = new ArrayList<String>();
    String[] words;
    if(separators.length() == 1) {
      words = defaultString.split(separators);
    } else {
      StringTokenizer st = new StringTokenizer(defaultString, separators);
      words = new String[st.countTokens()];
      for(int i = 0; st.hasMoreTokens(); ++i) {
        String temp = st.nextToken();
        if(temp != null) {
          words[i] = temp;
        }
      }
    }

    for (int i = 0; i < words.length; ++i) {
      boolean is16xNumber = true;
      for (int j = 0; j < words[i].length(); ++j) {
        int codeChar = words[i].codePointAt(j);
        if ((codeChar > '9' || codeChar < '0') && (codeChar < 'A' || codeChar > 'F')
            && ((codeChar < 'a' || codeChar > 'f'))) {
          is16xNumber = false;
          break;
        }
      }

      if (is16xNumber == true) {
        num16x.add(words[i].toLowerCase(Locale.ROOT));
      } else {
        notNum16x.add(words[i]);
      }
    }

    System.out.print("Hex number in string: ");
    String num16xStr = "";
    for (String s : num16x) {
      num16xStr += s + " ";
    }
    System.out.println(num16xStr);

    System.out.print("Not hex number in string: ");
    String notNum16xStr = "";
    for (String s : notNum16x) {
      notNum16xStr += s + " ";
    }
    System.out.println(notNum16xStr);

    System.out.print("Number with 1 more than 0: ");
    for (String s : num16x) {
      int counter1 = 0;
      int counter0 = 0;
      for(int i = 0; i < s.length(); ++i) {
        if(s.charAt(i) == '1') {
          ++counter1;
        }
        if (s.charAt(i) == '0') {
          ++counter0;
        }
      }

      if (counter1 > counter0) {
        System.out.print(s + " ");
      }
    }
    System.out.println();

    String randomNum = Integer.toHexString((int) (Math.random() * 1000));
    StringBuffer defaultStringBuffer = new StringBuffer(defaultString);

    System.out.print("Input place to insert random number: ");
    int place = in.nextInt();

    defaultStringBuffer.insert(place, " " + randomNum + " ");

    defaultString = defaultStringBuffer.toString().replaceAll("  ", " ");
    System.out.print("Added random number: ");
    System.out.println(defaultString);

    for (int i = 0; i < notNum16x.size(); ++i) {
      boolean isHaveLatLetter = true;
      for (int j = 0; j < notNum16x.get(i).length(); ++j) {
        char c = notNum16x.get(i).charAt(j);
        if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
          isHaveLatLetter = false;
          break;
        }
      }

      if (isHaveLatLetter == true) {
        if (!notNum16x.isEmpty()) {
          defaultString = defaultString.replace(notNum16x.get(i), "");
        }
        defaultString = defaultString.replaceAll("  ", " ");
        break;
      }
    }

    System.out.print("Default string after delete 1st lexeme with latin letters: ");
    System.out.println(defaultString);

    System.out.print("Input num P: ");
    Scanner cin = new Scanner(System.in);
    String P = cin.nextLine();

    System.out.println("Position number " + P + " is " + defaultString.indexOf(P + " "));

    if (!num16x.isEmpty()) {
      defaultString = defaultString.replace(num16x.get(0), "");
    }
    defaultString = defaultString.replaceAll("  ", " ");
    System.out.println("Default string after delete 1st hex number: " + defaultString);
  }
}
