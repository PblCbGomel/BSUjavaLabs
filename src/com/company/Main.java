package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.print("Input format date: ");
    String dateFormat = in.nextLine();

    System.out.print("Input date: ");
    String date = in.nextLine();

    DateWork d = new DateWork(date, dateFormat);
    d.printDate();
    d.printDateWithTime();
    System.out.println("Day number in year: " + d.getYearDayNumber());
    System.out.println("Day number in month: " + d.getMonthDayNumber());
    System.out.println("Day number in week: " + d.getWeekDayNumber());
    d.printAllNumbers();
    d.printWithSimpleDateFormat();
    d.setDate(1888, 11, 13);
    d.printWithFormatter();
  }
}
