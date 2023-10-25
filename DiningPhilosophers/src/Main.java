import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mrGreenNV
 */
public class Main {

    public final static int COUNT_PHILOSOPHER = 5;

    public static void main(String[] args) {
        final Philosopher[] philosophers = new Philosopher[COUNT_PHILOSOPHER];
        final Lock[] locks = new Lock[philosophers.length];

        for (int i = 0; i < locks.length; i++) {
            locks[i] = new ReentrantLock();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Lock leftFork = locks[i];
            Lock rightFork = locks[(i + 1) % locks.length];

            philosophers[i] = new Philosopher(leftFork, rightFork);

            Thread thread = new Thread(philosophers[i], "Философ " + (i + 1));
            thread.start();
        }
    }
}