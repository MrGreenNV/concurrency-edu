import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mrGreenNV
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);

        Thread t1 = new Thread(() -> {
            while (counter.get() < 1000000) {
                int newValue = counter.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " увеличил " + newValue);
            }
        });

        Thread t2 = new Thread(() -> {
            while (counter.get() > -1000000) {
                int newValue = counter.decrementAndGet();
                System.out.println(Thread.currentThread().getName() + " уменьшил " + newValue);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}