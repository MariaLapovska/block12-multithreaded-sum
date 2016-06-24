/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        int length = ArraySum.LENGTH;

        for (int i = 1; i <= ArraySum.RUNS; i++) {
            int[] array = ArraySum.createRandomArray(length);
            long startTime = System.currentTimeMillis();
            int total = 0;

            for (int j = 1; j <= 100; j++) {
                total = ArraySum.sum(array, ArraySum.THREAD_COUNT);
            }

            long endTime = System.currentTimeMillis();

            System.out.printf("%10d elements  =>  %6d ms, sum: %d\n", length, endTime - startTime, total);
            length *= 2;
        }
    }
}
