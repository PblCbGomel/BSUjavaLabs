package com.company;

import java.util.Scanner;

class Functions {

  public static double pow(int p, double num) {
    double result = 1;

    if (p == 0) {
      return result;
    } else if (p < 0) {
      p = -p;
      for (int i = 0; i < p; ++i) {
        result *= num;
      }
      result = 1 / result;
    } else {
      for (int i = 0; i < p; ++i) {
        result *= num;
      }
    }

    return result;
  }

  public static double sinxDivx(int epsPow, double realResult, double x) {
    double result = 0;
    double eps = pow(-epsPow, 10);

    for (int i = 1; Math.abs(result - realResult) > eps; i += 2) {
      double term = pow(i / 2, -1) * pow(i - 1, x);
      for (int j = 1; j <= i; ++j) {
        term /= j;
      }

      result += term;
    }

    return result;
  }
}

public class Main {

  public static void main(String[] args) {
    int k;
    double x;

    Scanner in = new Scanner(System.in);

    System.out.print("Input x: ");
    x = in.nextDouble();

    System.out.print("Input k: ");
    k = in.nextInt();

    double realResult = Math.sin(x) / x;
    double result = Functions.sinxDivx(k, realResult, x);

    System.out.println("Result: " + result);
    System.out.println("Real result: " + realResult);
    System.out.println("Error: " + Math.abs(result - realResult));
  }

}
