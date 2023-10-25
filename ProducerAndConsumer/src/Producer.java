import java.util.Random;

public class Producer implements Runnable {

    private final QueueFixedBuffer<Task> queue;

    public Producer(QueueFixedBuffer<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        while (true) {
            Task task = new Task();
            queue.offer(task);

            try {
                Thread.sleep(new Random().nextInt(50) + 100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("--Поток: " + Thread.currentThread().getName() + " добавил задачу с id: " + task.getId());
        }
    }
}
