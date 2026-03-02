package com.learning.dsa;

public class PrintDiagonals {

    public static void printDiagonals(int[][] matrix, int i, int j) {
        while (j>=0 && i<matrix.length) {
            System.out.print(matrix[i][j] + " ");
            matrix[i][j] = -1;
            j--; i++;
        }
        System.out.println();
    }

    public static void printDiagonals(int[][] matrix) {

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if(matrix[i][j]!=-1) {
                    printDiagonals(matrix, i, j);
                }
            }
        }
    }


    public static void printDiagonalsRowColWise(int[][] matrix) {
        for(int i=0;i<matrix[0].length;i++) {

            int currCol = i;
            int currRow = 0;

            while (currCol>=0 && currRow<matrix.length) {
                System.out.print(matrix[currRow][currCol] + " ");
                currRow++; currCol--;
            }

            System.out.println();
        }

        for(int i=1; i<matrix.length; i++) {

            int currRow = i;
            int currCol = matrix[0].length-1;

            while (currCol>=0 && currRow<matrix.length) {
                System.out.print(matrix[currRow][currCol] + " ");
                currRow++; currCol--;
            }

            System.out.println();
        }

        System.out.println();

    }


    public static void main(String[] args) {

        /**
         *
         * Print diagonals like forward slash
         * Given a rectangular matrix of elements in range of 0 to 100
         * Print the diagonals

         * Output for the below mentioned matrix is:
         * 1
         * 2 6
         * 3 7 11
         * 4 8 12
         * 5 9 13
         * 10 14
         * 15

         */
        int[][] matrix = {
                {1,  2,  3,  4,  5},
                {6,  7,  8,  9,  10},
                {11, 12, 13, 14, 15}
        };

        printDiagonals(matrix);

        System.out.println();
        System.out.println();

        int[][] matrix1 = {
                {1,  2,  3,  4,  5},
                {6,  7,  8,  9,  10},
                {11, 12, 13, 14, 15}
        };
        printDiagonalsRowColWise(matrix1);
    }

}
