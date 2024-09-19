package itstep.learning;


public class Basics {
    public void Run() {
        int[][] matrixA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrixB = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        int[][] resultMatrix = multiplyMatrices(matrixA, matrixB);


        printMatrices(matrixA, matrixB, resultMatrix);
    }

    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] resultMatrix = new int[rowsA][colsB];


        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                resultMatrix[i][j] = 0;
                for (int k = 0; k < colsA; k++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return resultMatrix;
    }

    public static void printMatrices(int[][] matrixA, int[][] matrixB, int[][] resultMatrix) {
        int size = matrixA.length;


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrixA[i][j] + " ");
            }
            if (i == 1) {
                System.out.print(" X  ");
            } else {
                System.out.print("    ");
            }
            for (int j = 0; j < size; j++) {
                System.out.print(matrixB[i][j] + " ");
            }
            if (i == 1) {
                System.out.print(" =  ");
            } else {
                System.out.print("    ");
            }
            for (int j = 0; j < size; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
