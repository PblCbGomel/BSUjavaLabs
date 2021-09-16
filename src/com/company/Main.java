package com.company;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

class Functions {

  public static BigDecimal power(BigDecimal num, BigInteger pow) {
    BigDecimal result = BigDecimal.ONE;

    if (result.compareTo(BigDecimal.ZERO) == 0) {
      if (pow.compareTo(BigInteger.ZERO) == 0) {
        return BigDecimal.ONE;
      } else {
        return BigDecimal.ZERO;
      }
    } else if (pow.compareTo(BigInteger.ZERO) < 0) {
      pow = pow.multiply(BigInteger.valueOf(-1));
      for (int i = 0; pow.compareTo(BigInteger.valueOf(i)) > 0; ++i) {
        result = result.multiply(num);
      }
      result = BigDecimal.ONE.divide(result);
    } else {
      for (int i = 0; pow.compareTo(BigInteger.valueOf(i)) > 0; ++i) {
        result = result.multiply(num);
      }
    }

    return result;
  }

  public static BigDecimal factorial(BigInteger num) {
    BigDecimal result = BigDecimal.ONE;

    if (num.compareTo(BigInteger.ONE) > 0) {
      for (int i = 2; num.compareTo(BigInteger.valueOf(i)) >= 0; ++i) {
        result = result.multiply(BigDecimal.valueOf(i));
      }
    }

    return result;
  }

  public static BigDecimal sinDivX(BigInteger epsPower, BigDecimal realNumber, BigDecimal x) {
    BigDecimal result = BigDecimal.valueOf(0);
    BigDecimal eps = power(BigDecimal.valueOf(10), epsPower.multiply(BigInteger.valueOf(-1)));
    MathContext mc = new MathContext(epsPower.intValue() * 10);

    for (BigInteger i = BigInteger.ZERO; ((result.subtract(realNumber)).abs()).compareTo(eps) > 0;
        i = i.add(BigInteger.TWO)) {
        result = result.add(power(BigDecimal.valueOf(-1), i.divide(BigInteger.TWO))
          .multiply(power(x, i).divide(factorial(i.add(BigInteger.ONE)), mc)));
    }

    return result;
  }

}

public class Main {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.print("Input eps power: ");
    BigInteger k = in.nextBigInteger();

    System.out.print("Input x: ");
    BigDecimal x = in.nextBigDecimal();

    BigDecimal realResult = BigDecimal.valueOf(Math.sin(x.doubleValue())/x.doubleValue());

    BigDecimal result = Functions.sinDivX(k, realResult, x);


    System.out.println("Result: " + String.format("%." + (k.add(BigInteger.ONE)) + "f", result));
    System.out.println("Real result: " + String.format("%." + (k.add(BigInteger.ONE)) + "f", realResult));
    System.out.println("Error: " + String.format("%." + (k.add(BigInteger.ONE)) + "f",result.subtract(realResult).abs()));
    System.out.println("Result in exponent performance: " + String.format("%e", result));
    System.out.println("Result in 16 system: " + String.format("%a", result.doubleValue()));
  }
}
