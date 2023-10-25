import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author mrGreenNV
 */
public class Philosopher implements Runnable {

    private final Lock leftFork;
    private final Lock rightFork;

    public Philosopher(Lock leftFork, Lock rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void eat() {
        try {
            System.out.println(Thread.currentThread().getName() + " ест");
            Thread.sleep(new Random().nextInt(1000) + 200);
        } catch (InterruptedException e) {
            System.out.println("Поесть не удалось");
            throw new RuntimeException(e);
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
            try {
                if (leftFork.tryLock(10, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " взял левую вилку");
                    System.out.println(Thread.currentThread().getName() + " пробует взять правую вилку");
                    if (rightFork.tryLock(10, TimeUnit.MICROSECONDS)) {
                        System.out.println(Thread.currentThread().getName() + " взял правую вилку");
                        eat();
                        rightFork.unlock();
                        System.out.println(Thread.currentThread().getName() + " вернул правую вилку");
                    } else {
                        System.out.println(Thread.currentThread().getName() + " не смог взять правую вилку");
                    }
                    leftFork.unlock();
                    System.out.println(Thread.currentThread().getName() + " вернул левую вилку");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
