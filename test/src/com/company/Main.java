package com.company;

import java.util.HashSet;


/*
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

1) Each row must contain the digits 1-9 without repetition.
2) Each column must contain the digits 1-9 without repetition.
3) Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'
*/
public class Main {

    private static boolean isValidSudoku(char[][] board) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char el = board[i][j];
                if (!checkElement(set, el, i, j)) {
                    return false;
                }
            }
        }

        // Your code should be here
        return true;
    }

    private static boolean checkElement(HashSet set, Character el, int i, int j) {
        if (el == '.') return true;
        if (el >= '1' && el <= '9') {
            if (!set.add(el + " row " + i) || !set.add(el + " column " + j) || !set.add(el + " box " + i / 3 + "x" + j / 3))
                return false;
        } else return false;
        return true;
    }


    private static boolean isValidSudokuWithArray(char[][] board) {
        int size = 9;
        for (int i = 0; i < size; i++) {
            boolean[] arrRow = new boolean[size];
            boolean[] arrColumn = new boolean[size];
            boolean[] arrBox = new boolean[size];
            for (int j = 0; j < size; j++) {
                if (containsDuplicate(board[i][j], arrRow)) return false;
                if (containsDuplicate(board[j][i], arrColumn)) return false;
                if (containsDuplicate(board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3], arrBox)) return false;
            }
        }
        return true;
    }

    private static boolean containsDuplicate(char el, boolean[] arr) {
        if (el != '.') {
            if (el >= '1' && el <= '9') {
                int ind = el - '1';
                if (!arr[ind]) {
                    arr[ind] = true;
                } else {
                    return true;
                }
            } else return true;
        }
        return false;
    }

    public static void main(String[] args) {

        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        boolean res = isValidSudoku(board);
        boolean res2 = isValidSudokuWithArray(board);

        System.out.println(res);
        System.out.println(res2);
    }

}