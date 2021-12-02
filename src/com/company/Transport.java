package com.company;

import java.io.Serializable;

public class Transport implements Serializable {

  protected String name;
  protected Integer registerNumber;
  protected String date;
  protected Integer life;

  public void print() {
    System.out.println(name + " " + registerNumber.toString() + " " + date + " " + life.toString());
  }

  public String toString() {
    return name + " " + registerNumber + " " + date + " " + life;
  }

  public String getName() {
    return name;
  }

  public int getNumber() {
    return registerNumber;
  }

  public String getDate() {
    return date;
  }

  public int getLife() {
    return life;
  }

  public void setName(String name_) {
    name = name_;
  }

  public void setNumber(int number) {
    registerNumber = number;
  }

  public void setDate(String date_) {
    date = date_;
  }

  public void setLife(int life_) {
    life = life_;
  }
}
