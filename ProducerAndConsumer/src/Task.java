import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {

    private static AtomicInteger count = new AtomicInteger(0);

    private volatile int id;

    public Task() {
        this.id = count.incrementAndGet();
    }
    public int getId() {
        return this.id;
    }

    public void work() {
        System.out.println("Задача с id: " + getId() + " - начала работу");

        try {
            Thread.sleep(new Random().nextInt(100) + 500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Задача с id: " + getId() + " - завершила работу работу");
    }
}
