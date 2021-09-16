package com.company;

import java.util.Formatter;

public class Main {

    public static void main(String[] args) {
        Formatter frm1 = new Formatter();
        frm1.format("%o %x", 16, 16);
        System.out.println("Point 1: " + frm1);

        Formatter frm2 = new Formatter();
        frm2.format("%f", 16.);
        System.out.println("Point 2: " + frm2);

        Formatter frm3 = new Formatter();
        frm3.format("%20f", 16.12);
        System.out.println("Point 3: " + frm3);

        Formatter frm4 = new Formatter();
        frm4.format("%.6f", 16.123456789);
        System.out.println("Point 4: " + frm4);

        Formatter frm5 = new Formatter();
        frm5.format("%+.3f %n%,.06f %n%-7d %n%(.3f %n% f", 16.123, 4325123456.7891, 123, -12., -13.);
        System.out.println("Point 5:\n" + frm5);

        Formatter frm6 = new Formatter();
        frm6.format("%3$d %5$d %4$d %1$d %2$d", 1, 2, 3, 4, 5);
        System.out.println("Point 6: " + frm6);
    }
}
