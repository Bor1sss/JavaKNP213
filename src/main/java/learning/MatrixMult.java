package learning;

public class MatrixMult {

    public static int[][] matrixMult(int[][] matrixA, int[][] matrixB) {
        int rows = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] result = new int[rows][colsB];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colsB; j++) {
                result[i][j] = 0;
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }

    public static void output(int[][] matrixA, int[][] matrixB, int[][] result) {
        int rows = matrixA.length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < matrixA[i].length; j++) {
                System.out.print(matrixA[i][j] + " ");
            }

            if (i == rows / 2) {
                System.out.print("  X  ");
            } else {
                System.out.print("     ");
            }

            for (int j = 0; j < matrixB[i].length; j++) {
                System.out.print(matrixB[i][j] + " ");
            }

            if (i == rows / 2) {
                System.out.print("  =  ");
            } else {
                System.out.print("     ");
            }

            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void run() {
        int[][] matrixA = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        int[][] matrixB = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        int[][] result = matrixMult(matrixA, matrixB);

        output(matrixA, matrixB, result);
    }

}
