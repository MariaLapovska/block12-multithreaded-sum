/**
 * Helper class to store sum of array's elements
 */
public class Summer implements Runnable {
    private int[] array;
    private int min, max;
    private int sum;

    public Summer(int[] array, int min, int max) {
        this.array = array;
        this.min = min;
        this.max = max;
    }

    public int getSum() {
        return sum;
    }

    public void run() {
        this.sum = ArraySum.sumRange(array, min, max);
    }
}