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
        while(!xWin(field) && !oWin(field) && !nobodyWon(field)) {
            String input = in.nextLine();
            String[] data = input.split(" ");
            Integer[] coords = mapToInt(data);
            if (!validate(field, coords)) {
                System.out.println("Введите координаты снова. Предыдущие данные некорректны");
                continue;
            }
            if (turn) {
                field[coords[0]][coords[1]] = 1;
                turn = false;
                if (xWin(field)) {
                    System.out.println("X WON");
                }
            } else {
                field[coords[0]][coords[1]] = 2;
                turn = true;
                if (oWin(field)) {
                    System.out.println("O WON");
                }
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

    private static boolean validate(int[][] field, Integer[] coords) {
        if (coords[0] < 0 || coords[0] > 2) {
            return false;
        }
        if (coords[1] < 0 || coords[1] > 2) {
            return false;
        }
        if (field[coords[0]][coords[1]] != 0) {
            return false;
        }
        return true;
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

    private static boolean allInArray(int[] a, int template) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != template) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAWinner(int[][] field, int player) {
        for (int i = 0; i < 3; i++) {
            if (allInArray(extractRow(field, i), player)) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (allInArray(extractColumn(field, j), player)) {
                return true;
            }
        }
        if (allInArray(extractMainDiag(field), player)) {
            return true;
        }
        if (allInArray(extractSlaveDiag(field), player)) {
            return true;
        }
        return false;
    }

    private static boolean xWin(int[][] field) {
        return isAWinner(field, 1);
    }

    private static boolean oWin(int[][] field) {
        return isAWinner(field, 2);
    }

    private static boolean nobodyWon(int[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
