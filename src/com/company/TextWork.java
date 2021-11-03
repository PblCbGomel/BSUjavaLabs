package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextWork {
  private static String generateRandomWord() {
    Random random = new Random();
    char[] word = new char[random.nextInt(8)+3];

    for(int j = 0; j < word.length; j++) {
        word[j] = (char)('a' + random.nextInt(26));
      }

    return new String(word);

  }

  private static ArrayList<String> readStringsFromFile(BufferedReader fromFile) {
    ArrayList<String> result = new ArrayList<>();
    String str;

    try {
      while ((str = fromFile.readLine()) != null) {
        result.add(str);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }

  private static void writeEndTextInFile(ArrayList<String> fileString) {
    try {
      BufferedWriter fileOutStrong = new BufferedWriter(
          new OutputStreamWriter(
              new FileOutputStream("ResultText.txt"), StandardCharsets.UTF_8));

      for(String s : fileString) {
        fileOutStrong.write(s + "\n");
      }

      fileOutStrong.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("File \"ResultText.txt\" was created.");
  }

  public static void changeWordsInText(String fileName) {
    BufferedReader fileIn;

    try {
      fileIn = new BufferedReader(
          new InputStreamReader(
            new FileInputStream(fileName), StandardCharsets.UTF_8));

      ArrayList<String> fileString = readStringsFromFile(fileIn);
      int size = fileString.size();

      for(int i = 0; i < size; ++i) {
        if(fileString.get(i).equals("")) {
          fileString.add("");
          continue;
        }
        String[] copy = fileString.get(i).split("[ )(*+-/^!@#%&.,?]");
        for(int j = 0; j < copy.length; ++j) {
          copy[j] = generateRandomWord();
        }
        fileString.add(Arrays.stream(copy).collect(Collectors.joining(" ")));
      }

      size = fileString.size();

      while(size / 2 != fileString.size()) {
        fileString.remove(0);
      }

      writeEndTextInFile(fileString);

      fileIn.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
