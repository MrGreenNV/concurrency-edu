import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mrGreenNV
 */
public class Counter {
    private int count;

    public Counter() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public synchronized void inc() {
        this.count++;
    }

    public synchronized void dec() {
        this.count--;
    }
}
