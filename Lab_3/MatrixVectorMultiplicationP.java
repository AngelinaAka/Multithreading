import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class MatrixVectorMultiplicationP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод размеров матрицы и вектора
        System.out.println("Введите количество строк матрицы:");
        int rows = scanner.nextInt();
        System.out.println("Введите количество столбцов матрицы:");
        int cols = scanner.nextInt();

        // Ввод матрицы
        int[][] matrix = inputMatrix(scanner, rows, cols);

        // Ввод вектора
        System.out.println("Введите вектор:");
        int[] vector = new int[cols];
        for (int i = 0; i < cols; i++) {
            vector[i] = scanner.nextInt();
        }

        // Вычисление времени выполнения для разных количеств потоков
        List<Integer> numThreadsList = new ArrayList<>();
        List<Long> executionTimes = new ArrayList<>();
        for (int numThreads = 1; numThreads <= rows; numThreads++) {
            long startTime = System.nanoTime();
            int[] result = multiplyMatrixVector(matrix, vector, numThreads);
            long endTime = System.nanoTime();

            numThreadsList.add(numThreads);
            executionTimes.add(endTime - startTime);

            // Вывод результатов
            System.out.println("Количество потоков: " + numThreads);
            System.out.println("Время выполнения: " + (endTime - startTime) + " наносекунд");
            //System.out.println("Время выполнения: " + executionTime / 1000000 + " миллисекунд");
            System.out.println("Результат:");
            for (int i = 0; i < rows; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            System.out.println();
        }
        //Таблица
        System.out.println("+--------------------+-------------------------------+");
        System.out.println("| "+"Количество потоков"+" | "+"Время выполнения (наносекунды)"+ "|");
        for (int i = 0; i < numThreadsList.size(); i++) {
            System.out.println("| " + numThreadsList.get(i)+ "                  |" + " " + executionTimes.get(i));
        }
        System.out.println("+--------------------+-------------------------------+");
    }

    // Функция для ввода матрицы
    static int[][] inputMatrix(Scanner scanner, int rows, int cols) {
        System.out.println("Введите матрицу:");
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    // Функция умножения матрицы на вектор с использованием потоков
    static int[] multiplyMatrixVector(int[][] matrix, int[] vector, int numThreads) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] result = new int[rows];

        // Создаем пул потоков
        Thread[] threads = new Thread[numThreads];

        // Разбиваем матрицу на части для обработки потоками
        int rowsPerThread = rows / numThreads;
        int remainder = rows % numThreads;

        // Создаем и запускаем потоки
        for (int i = 0; i < numThreads; i++) {
            int startRow = i * rowsPerThread;
            int endRow = startRow + rowsPerThread;
            if (i == numThreads - 1) {
                endRow += remainder;
            }
            threads[i] = new Thread(new MatrixVectorMultiplier(matrix, vector, result, startRow, endRow));
            threads[i].start();
        }

        // Ожидаем завершения всех потоков
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // Внутренний класс для выполнения умножения части матрицы на вектор
    static class MatrixVectorMultiplier implements Runnable {
        private final int[][] matrix;
        private final int[] vector;
        private final int[] result;
        private final int startRow;
        private final int endRow;

        public MatrixVectorMultiplier(int[][] matrix, int[] vector, int[] result, int startRow, int endRow) {
            this.matrix = matrix;
            this.vector = vector;
            this.result = result;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public void run() {
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < vector.length; j++) {
                    result[i] += matrix[i][j] * vector[j];
                }
            }
        }
    }
}
