import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MaxElementFinder {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] array = {10, 5, 8, 3, 15, 20, 7, 12}; // Ваш массив данных

        int numThreads = Runtime.getRuntime().availableProcessors(); // Количество доступных процессоров
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        List<Callable<Integer>> tasks = new ArrayList<>();
        int chunkSize = array.length / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? array.length : (i + 1) * chunkSize;

            Callable<Integer> task = () -> {
                int max = Integer.MIN_VALUE;
                for (int j = start; j < end; j++) {
                    max = Math.max(max, array[j]);
                }
                return max;
            };

            tasks.add(task);
        }

        List<Future<Integer>> results = executor.invokeAll(tasks);
        executor.shutdown();

        int globalMax = Integer.MIN_VALUE;
        for (Future<Integer> result : results) {
            globalMax = Math.max(globalMax, result.get());
        }

        System.out.println("Максимальный элемент в массиве: " + globalMax);
    }
}

