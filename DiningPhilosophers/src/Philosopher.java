import java.util.Random;

/**
 * @author mrGreenNV
 */
public class Philosopher implements Runnable {

    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void eat() {
        try {
            System.out.println(Thread.currentThread().getName() + " ест");
            Thread.sleep(new Random().nextInt(100) + 200);
        } catch (InterruptedException e) {
            throw new RuntimeException("Поесть не удалось", e);
        }
    }

    public void think() {
        try {
            System.out.println(Thread.currentThread().getName() + " думает");
            Thread.sleep(new Random().nextInt(50) + 100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            think();
            synchronized (leftFork) {
                System.out.println(Thread.currentThread().getName() + " взял левую вилку");
                System.out.println(Thread.currentThread().getName() + " пробует взять правую вилку");
                if (tryToPickRightFork()) {
                    System.out.println(Thread.currentThread().getName() + " взял правую вилку");
                    eat();
                    System.out.println(Thread.currentThread().getName() + " вернул правую вилку");
                } else {
                    System.out.println(Thread.currentThread().getName() + " не смог взять правую вилку");
                }

                System.out.println(Thread.currentThread().getName() + " вернул левую вилку");
            }
        }
    }

    private boolean tryToPickRightFork() {
        synchronized (rightFork) {
            try {
                Thread.sleep(new Random().nextInt(10) + 5);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " вилка занята");
                return false;
            }
            return true;
        }
    }

}
