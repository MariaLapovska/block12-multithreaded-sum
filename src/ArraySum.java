import java.util.Random;

/**
 * Class to count sum of array's elements
 * using multiple threads
 */
public class ArraySum {

    /** Random number generator */
    private static final Random RAND = new Random(42);

    /** Initial length of array to sort */
    public static final int LENGTH = 1000;

    /** How many times to grow by 2 */
    public static final int RUNS = 15;

    /** Number of threads */
    public static final int THREAD_COUNT = 4;

    /**
     * Computes the total sum of all elements of the given array.
     * Parallel version that can use any number of threads.
     * @param array Array to calculate sum of
     * @param threadCount Number of threads to use
     * @return Sum of array's elements
     */
    public static int sum(int[] array, int threadCount) {
        int length = (int) Math.ceil(1.0 * array.length / threadCount);
        Summer[] summers = new Summer[threadCount];
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            summers[i] = new Summer(array, i * length, Math.min((i + 1) * length, array.length));
            threads[i] = new Thread(summers[i]);
            threads[i].start();
        }

        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int total = 0;

        for (Summer summer : summers) {
            total += summer.getSum();
        }
        return total;
    }

    /**
     * Helper method to compute sum of array a, indexes [min .. max).
     * @param a Array to compute
     * @param min Min index
     * @param max Max index
     * @return Sum of elements of array between min index (inclusive)
     * and max index (exclusive)
     */
    public static int sumRange(int[] a, int min, int max) {
        int result = 0;

        for (int i = min; i < max; i++) {
            result += a[i];
        }
        return result;
    }

    /**
     * Creates an array of the given length, fills it with random
     * non-negative integers, and returns it.
     * @param length Length of array to create
     * @return Array of given length, filled with random numbers
     */
    public static int[] createRandomArray(int length) {
        int[] array = new int[length];

        for (int i = 0; i < array.length; i++) {
            array[i] = RAND.nextInt(50);
        }
        return array;
    }
}
