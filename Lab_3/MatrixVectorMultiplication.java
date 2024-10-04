import java.util.Arrays;
import java.util.Scanner;

public class MatrixVectorMultiplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод матрицы
        System.out.println("Введите количество строк матрицы:");
        int rows = scanner.nextInt();
        System.out.println("Введите количество столбцов матрицы:");
        int cols = scanner.nextInt();
        int[][] matrixA = new int[rows][cols];
        System.out.println("Введите матрицу:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixA[i][j] = scanner.nextInt();
            }
        }

        // Ввод вектора
        System.out.println("Введите размер вектора:");
        int vectorSize = scanner.nextInt();
        int[] vector = new int[vectorSize];
        System.out.println("Введите вектор:");
        for (int i = 0; i < vectorSize; i++) {
            vector[i] = scanner.nextInt();
        }

        // Проверка совместимости размеров
        if (cols != vectorSize) {
            System.err.println("Количество столбцов матрицы должно быть равно размеру вектора.");
            return;
        }

        // Измерение времени выполнения
        long startTime = System.nanoTime();
        int[] resultVector = multiplyMatrixByVector(matrixA, vector);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Вывод результата
        System.out.println("Результат умножения матрицы на вектор:");
        System.out.println(Arrays.toString(resultVector));

        // Вывод времени выполнения
        System.out.println("Время выполнения: " + executionTime + " наносекунд");
        //System.out.println("Время выполнения: " + executionTime / 1000000 + " миллисекунд");
    }

    // Функция умножения матрицы на вектор
    public static int[] multiplyMatrixByVector(int[][] matrixA, int[] vector) {
        int rows = matrixA.length;
        int cols = matrixA[0].length;
        int[] resultVector = new int[rows];

        // Линейный код умножения
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultVector[i] += matrixA[i][j] * vector[j];
            }
        }

        return resultVector;
    }
}
