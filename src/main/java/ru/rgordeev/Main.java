package ru.rgordeev;

import java.util.Scanner;

public class Main {

    static int[][] field = new int[3][3];
    static boolean turn = true;


    private static void init() {
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = 0;
            }
        }
    }

    private static void printField() {
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        init();
        printField();
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            String input = in.nextLine();
            String[] data = input.split(" ");
            Integer[] coords = mapToInt(data);
            if (turn) {
                field[coords[0]][coords[1]] = 1;
                turn = false;
            } else {
                field[coords[0]][coords[1]] = 2;
                turn = true;
            }
            printField();
        }
    }

    private static void print(Object[] data) {
        for (Object literal: data) {
            System.out.println(literal);
        }
    }

    private static Integer[] mapToInt(String[] input) {
        Integer[] result = new Integer[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = Integer.valueOf(input[i]);
        }
        return result;
    }

    private static int[] extractRow(int[][] field, int i) {
        return field[i];
    }

    private static int[] extractColumn(int[][] field, int j) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][j];
        }
        return result;
    }

    private static int[] extractMainDiag(int[][] field) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][i];
        }
        return result;
    }

    private static int[] extractSlaveDiag(int[][] field) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][2 - i];
        }
        return result;
    }
}
