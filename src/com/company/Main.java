package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

class DoubleMatrixWork {

  public static void inputMatrix(Double matrix[][], int matrixSize) {
    Scanner in = new Scanner(System.in);

    for (int i = 0; i < matrixSize; ++i) {
      for (int j = 0; j < matrixSize; ++j) {
        System.out.print("Input [" + i + "][" + j + "] element in matrix: ");
        matrix[i][j] = in.nextDouble();
      }
    }
  }

  public static void randomFilling(Double matrix[][], int matrixSize) {
    for (int i = 0; i < matrixSize; ++i) {
      for (int j = 0; j < matrixSize; ++j) {
        matrix[i][j] = Math.random() * 100;
        System.out.println("[" + i + "][" + j + "] element in matrix = " + matrix[i][j]);
      }
    }
  }

  public static void outputMatrix(Double matrix[][], int matrixSize) {
    for (int i = 0; i < matrixSize; ++i) {
      for (int j = 0; j < matrixSize; ++j) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void formatOutputMatrix(Double matrix[][], int matrixSize) {
    for (int i = 0; i < matrixSize; ++i) {
      for (int j = 0; j < matrixSize; ++j) {
        if (i == 0) {
          DecimalFormat percentFormat = new DecimalFormat("¤");
          System.out.print(percentFormat.format(matrix[i][j]) + " ");
        } else if (i == 1) {
          DecimalFormat percentFormat = new DecimalFormat("%");
          System.out.print(percentFormat.format(matrix[i][j]) + " ");
        } else {
          DecimalFormat percentFormat = new DecimalFormat("###.##");
          System.out.print(percentFormat.format(matrix[i][j]) + " ");
        }
      }
      System.out.println();
    }
  }

  public static boolean isEqual(Double matrix1[][], Double matrix2[][], int matricesSize) {
    for (int i = 0; i < matricesSize; ++i) {
      for (int j = 0; j < matricesSize; ++j) {
        if (Math.abs(matrix1[i][j] - matrix2[i][j]) > Math.ulp(1.0)) {
          return false;
        }
      }
    }

    return true;
  }

  public static void sideDiagonalTransposition(Double matrix[][], int matrixSize) {
    for (int i = 0; i < matrixSize; ++i) {
      for (int j = 0; j < matrixSize - i - 1; ++j) {
        matrix[i][j] += matrix[matrixSize - j - 1][matrixSize - i - 1];
        matrix[matrixSize - j - 1][matrixSize - i - 1] =
            matrix[i][j] - matrix[matrixSize - j - 1][matrixSize - i - 1];
        matrix[i][j] = matrix[i][j] - matrix[matrixSize - j - 1][matrixSize - i - 1];
      }
    }
  }

  public static void mainDiagonalTransposition(Double matrix[][], int matrixSize) {
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
      return 1;
    } else if (a < b) {
      return -1;
    } else {
      return 0;
    }
  }

  public static int backCompare(double a, double b) {
    if (a > b) {
      return -1;
    } else if (a < b) {
      return 1;
    } else {
      return 0;
    }
  }

  public static void sortLastLine(Double matrix[][], int matrixSize) {
    Arrays.sort(matrix[matrixSize - 1], (a, b) -> backCompare(a, b));
  }

  public static boolean binarySearchInLine(Double matrix[][], double num, int lineNumber) {
    Arrays.sort(matrix[lineNumber], (a, b) -> compare(a, b));
    return Arrays.binarySearch(matrix[lineNumber], num) != -1;
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.print("Input size of arrays: ");
    int size = in.nextInt();

    System.out.println("Matrix 1: ");
    Double[][] mat1 = new Double[size][size];
    DoubleMatrixWork.inputMatrix(mat1, size);

    System.out.println("Matrix 2: ");
    Double[][] mat2 = new Double[size][size];
    DoubleMatrixWork.inputMatrix(mat2, size);

    System.out.println("Matrix 1: ");
    DoubleMatrixWork.outputMatrix(mat1, size);
    System.out.println("===========================================");
    System.out.println("Matrix 2: ");
    DoubleMatrixWork.outputMatrix(mat2, size);

    System.out.println("===========================================");
    System.out.println("================Reulst work================");
    System.out.println("===========================================");

    if (DoubleMatrixWork.isEqual(mat1, mat2, size)) {
      System.out.println(
          "The second path of a finite transposition number CAN be obtained from the first matrix.");
    } else {
      DoubleMatrixWork.mainDiagonalTransposition(mat1, size);
      if (DoubleMatrixWork.isEqual(mat1, mat2, size)) {
        System.out.println(
            "The second path of a finite transposition number CAN be obtained from the first matrix.");
      } else {
        DoubleMatrixWork.mainDiagonalTransposition(mat1, size);
        DoubleMatrixWork.sideDiagonalTransposition(mat1, size);
        if (DoubleMatrixWork.isEqual(mat1, mat2, size)) {
          System.out.println(
              "The second path of a finite transposition number CAN be obtained from the first matrix.");
        } else {
          DoubleMatrixWork.mainDiagonalTransposition(mat1, size);
          if (DoubleMatrixWork.isEqual(mat1, mat2, size)) {
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
        DoubleMatrixWork.binarySearchInLine(mat2, num, lineNum));
  }
}
