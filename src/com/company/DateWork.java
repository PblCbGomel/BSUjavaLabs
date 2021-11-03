package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;

public class DateWork {

  Date date;
  Calendar calendar;

  public DateWork(int year, int month, int day, int h, int m, int s) {
    SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
    try {
      format.setLenient(false);
      date = format.parse(day + " " + month + " " + year + " " + h + ":" + m + ":" + s);
      calendar = new GregorianCalendar(year, month, day);
    } catch (ParseException e) {
      date = new Date();
      calendar = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDay());
    }
  }

  public DateWork(String d, String f) {
    SimpleDateFormat format = new SimpleDateFormat(f);
    try {
      date = format.parse(d);
      calendar = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDay());
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public Integer getYearDayNumber() {
    return calendar.get(GregorianCalendar.DAY_OF_YEAR);
  }

  public Integer getMonthDayNumber() {
    return calendar.DAY_OF_MONTH;
  }

  public Integer getWeekDayNumber() {
    return calendar.DAY_OF_WEEK;
  }

  public void printAllNumbers() {
    System.out.println(calendar.get(GregorianCalendar.DAY_OF_YEAR) + " " + calendar.DAY_OF_MONTH
        + " " + calendar.DAY_OF_WEEK);
  }

  public void printDate() {
    System.out.println(date.getDate() + " " + (date.getMonth() + 1) + " " + date.getYear());
  }

  public void printDateWithTime() {
    System.out.println(date);
  }

  public void printWithSimpleDateFormat() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy MMMM, F неделя hh:mm a, 'TimeZone:' Z");
    System.out.println(format.format(date));
  }

  public void printWithFormatter() {
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
    SimpleDateFormat timeFormat = new SimpleDateFormat("aa");
    String dayName = sdf.format(calendar.DAY_OF_WEEK);
    String timeType = timeFormat.format(date);

    Formatter format = new Formatter();
    System.out.println(format.format("Name of Day: %s; Month number: %d; AM/PM: %s; Time: %d:%d",
        dayName, date.getMonth(), timeType, date.getHours(), date.getMinutes()));
  }

  public void setDate(int year, int month, int day) {
    date.setYear(year);
    date.setMonth(month);
    date.setDate(day);
    calendar = new GregorianCalendar(year, month, day);
  }
}
