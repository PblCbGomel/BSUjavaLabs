package com.company;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

class DoubleMatrixWork {

  public static void inputMatrix(double matrix[][], int matrixSize) {
    Scanner in = new Scanner(System.in);

    for (int i = 0; i < matrixSize; ++i) {
      for (int j = 0; j < matrixSize; ++j) {
        System.out.print("Input [" + i + "][" + j + "] element in matrix: ");
        matrix[i][j] = in.nextDouble();
      }
    }
  }

  public static void randomFilling(double matrix[][], int matrixSize) {
    for (int i = 0; i < matrixSize; ++i) {
      for (int j = 0; j < matrixSize; ++j) {
        matrix[i][j] = Math.random() * 100;
        System.out.println("[" + i + "][" + j + "] element in matrix = " + matrix[i][j]);
      }
    }
  }

  public static void outputMatrix(double matrix[][], int matrixSize) {
    for (int i = 0; i < matrixSize; ++i) {
      for (int j = 0; j < matrixSize; ++j) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void formatOutputMatrix(double matrix[][], int matrixSize) {
    for (int i = 0; i < matrixSize; ++i) {
      for (int j = 0; j < matrixSize; ++j) {
        if (i == 0) {
          DecimalFormat procentFormat = new DecimalFormat("¤¤");
          System.out.print(procentFormat.format(matrix[i][j]) + " ");
        } else if (i == 1) {
          DecimalFormat procentFormat = new DecimalFormat("%");
          System.out.print(procentFormat.format(matrix[i][j]) + " ");
        } else {
          DecimalFormat procentFormat = new DecimalFormat("###.##");
          System.out.print(procentFormat.format(matrix[i][j]) + " ");
        }
      }
      System.out.println();
    }
  }

  public static boolean isEquale(double matrix1[][], double matrix2[][], int matricesSize) {
    for (int i = 0; i < matricesSize; ++i) {
      for (int j = 0; j < matricesSize; ++j) {
        if (matrix1[i][j] != matrix2[i][j]) {
          return false;
        }
      }
    }

    return true;
  }

  public static void sideDiagonalTransposition(double matrix[][], int matrixSize) {
    for (int i = 0; i < matrixSize; ++i) {
      for (int j = 0; j < matrixSize - i - 1; ++j) {
        matrix[i][j] += matrix[matrixSize - j - 1][matrixSize - i - 1];
        matrix[matrixSize - j - 1][matrixSize - i - 1] =
            matrix[i][j] - matrix[matrixSize - j - 1][matrixSize - i - 1];
        matrix[i][j] = matrix[i][j] - matrix[matrixSize - j - 1][matrixSize - i - 1];
      }
    }
  }

  public static void mainDiagonalTransposition(double matrix[][], int matrixSize) {
    for (int i = 0; i < matrixSize; ++i) {
      for (int j = 0; j < i; ++j) {
        matrix[i][j] += matrix[j][i];
        matrix[j][i] = matrix[i][j] - matrix[j][i];
        matrix[i][j] = matrix[i][j] - matrix[j][i];
      }
    }
  }

  public static int compare(double a, double b) {
    if (a > b) {
      return -1;
    } else if (a < b) {
      return 1;
    } else {
      return 0;
    }
  }

  public static void sortLastLine(double matrix[][], int matrixSize) {
    double[] lastLine = new double[matrixSize];

    for (int i = 0; i < matrixSize; ++i) {
      lastLine[i] = matrix[matrixSize - 1][i];
    }

    double[] sortLine = Arrays.stream(lastLine).boxed().sorted((a, b) -> compare(a, b))
        .mapToDouble(i -> i).toArray();

    for (int i = 0; i < matrixSize; ++i) {
      matrix[matrixSize - 1][i] = sortLine[i];
    }
  }

  public static boolean binarySearchInLine(double matrix[][], int matrixSize, double num, int lineNumber) {
    double[] line = Arrays.stream(matrix[lineNumber - 1]).boxed().sorted().mapToDouble(i -> i).toArray();

    int indexResult = Arrays.binarySearch(line, num);

    return indexResult != -1;
  }
}

public class Main {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.print("Input size of arrays: ");
    int size = in.nextInt();

    System.out.println("Matrix 1: ");
    double[][] mat1 = new double[size][size];
    DoubleMatrixWork.inputMatrix(mat1, size);

    System.out.println("Matrix 2: ");
    double[][] mat2 = new double[size][size];
    DoubleMatrixWork.inputMatrix(mat2, size);

    System.out.println("Matrix 1: ");
    DoubleMatrixWork.outputMatrix(mat1, size);
    System.out.println("===========================================");
    System.out.println("Matrix 2: ");
    DoubleMatrixWork.outputMatrix(mat2, size);
    System.out.println("===========================================");
    System.out.println("================Reulst work================");
    System.out.println("===========================================");

    if (DoubleMatrixWork.isEquale(mat1, mat2, size)) {
      System.out.println(
          "The second path of a finite transposition number CAN be obtained from the first matrix.");
    } else {
      DoubleMatrixWork.mainDiagonalTransposition(mat1, size);
      if (DoubleMatrixWork.isEquale(mat1, mat2, size)) {
        System.out.println(
            "The second path of a finite transposition number CAN be obtained from the first matrix.");
      } else {
        DoubleMatrixWork.mainDiagonalTransposition(mat1, size);
        DoubleMatrixWork.sideDiagonalTransposition(mat1, size);
        if (DoubleMatrixWork.isEquale(mat1, mat2, size)) {
          System.out.println(
              "The second path of a finite transposition number CAN be obtained from the first matrix.");
        } else {
          DoubleMatrixWork.mainDiagonalTransposition(mat1, size);
          if (DoubleMatrixWork.isEquale(mat1, mat2, size)) {
            System.out.println(
                "The second path of a finite transposition number CAN be obtained from the first matrix.");
          } else {
            System.out.println(
                "The second path of the finite transposition number CANNOT be obtained from the first matrix.");
          }
        }
      }
    }

    System.out.println("===========================================");
    System.out.println("Matrix 2 format output: ");
    DoubleMatrixWork.formatOutputMatrix(mat2, size);

    System.out.println("===========================================");
    System.out.println("Matrix 2 after sort last line: ");
    DoubleMatrixWork.sortLastLine(mat2, size);
    DoubleMatrixWork.outputMatrix(mat2, size);

    System.out.println("===========================================");
    System.out.println("===========Matrix2 binary search===========");
    System.out.print("Input number to search: ");
    double num = in.nextDouble();
    System.out.print("Input line number: ");
    int lineNum = in.nextInt();
    System.out.print("Number " + num + " is in " + lineNum + " line: " +
        DoubleMatrixWork.binarySearchInLine(mat2, size, num, lineNum));
  }
}
