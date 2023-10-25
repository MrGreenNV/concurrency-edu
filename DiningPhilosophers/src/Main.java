/**
 * @author mrGreenNV
 */
public class Main {

    public final static int COUNT_PHILOSOPHER = 2;

    public static void main(String[] args) {
        final Philosopher[] philosophers = new Philosopher[COUNT_PHILOSOPHER];
        final Fork[] forks = new Fork[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % forks.length];

            philosophers[i] = new Philosopher(leftFork, rightFork);

            Thread thread = new Thread(philosophers[i], "Философ " + (i + 1));
            thread.start();
        }
    }
}