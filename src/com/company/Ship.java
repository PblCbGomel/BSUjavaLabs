package com.company;


import java.io.Serializable;

public class Ship extends Transport implements Serializable {

  public Ship() {
    name = "NoName";
    registerNumber = 0;
    date = "01.01.1970";
    life = 0;
  }

  public Ship(String name_, Integer registerNumber_, String date_, Integer life_) {
    name = name_;
    registerNumber = registerNumber_;
    date = date_;
    life = life_;
  }

  public Ship(Ship sh) {
    name = sh.name;
    registerNumber = sh.registerNumber;
    date = sh.date;
    life = sh.life;
  }

  @Override
  public void print() {
    System.out.println(name + " " + registerNumber.toString() + " " + date + " " + life.toString());
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getNumber() {
    return registerNumber;
  }

  @Override
  public String getDate() {
    return date;
  }

  @Override
  public int getLife() {
    return life;
  }

  @Override
  public void setName(String name_) {
    name = name_;
  }

  @Override
  public void setNumber(int number) {
    registerNumber = number;
  }

  @Override
  public void setDate(String date_) {
    date = date_;
  }

  @Override
  public void setLife(int life_) {
    life = life_;
  }

  @Override
  public String toString() {
    return name + " " + registerNumber + " " + date + " " + life;
  }

  @Override
  public boolean equals(Object sh2) {
    return this.getClass() == sh2.getClass()
        && this.name.equals(((Ship) sh2).name)
        && this.registerNumber.equals(((Ship) sh2).registerNumber)
        && this.date.equals(((Ship) sh2).name)
        && this.life.equals(((Ship) sh2).life);
  }

}
