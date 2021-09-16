package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    //Проверка строки из ТЗ
    @Test
    public void testTechnicalTaskString() {
      String result = App.compressString("aaaabbbc");
      assertEquals("a4b3c", result);
    }

    //Проверка строки из пробелов
    @Test
    public void testSpacesString() {
      String result = App.compressString("   ");
      assertEquals(" 3", result);
    }

    //Проверка пустой строки
    @Test
    public void testEmptyString() {
      String result = App.compressString("");
      assertEquals("", result);
    }

    //Проверка строки из одного символа
    @Test
    public void testStringWithOneSymbol() {
      String result = App.compressString("_");
      assertEquals("_", result);
    }

    //Проверка строки, состоящей только из символов
    @Test
    public void testSignsString() {
      String result = App.compressString("***&&&&{{{{$$$$$--/");
      assertEquals("*3&4{4$5-2/", result);
    }

    //Проверка обычной строки
    @Test
    public void testDefaultString() {
      String result = App.compressString("Hello, I'm Jenya!!! Who are you?");
      assertEquals("Hel2o, I'm Jenya!3 Who are you?", result);
    }

    //Проверка строки с цифрами
    @Test
    public void testStringWithNumbers() {
      String result = App.compressString("лллддкккв");
      assertEquals("л3д2к3в", result);
    }
}
