import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author mrGreenNV
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean allTaskAdded = new AtomicBoolean(false);

        ExecutorService producerService = Executors.newFixedThreadPool(4);
        ExecutorService consumerService = Executors.newFixedThreadPool(5);

        BlockingQueue<Task> queue = new ArrayBlockingQueue<>(10);

        producerService.execute(() -> {
            while (true) {
                Task task = new Task();
                if (task.getId() == 100) {
                    break;
                }
                try {
                    queue.put(task);
                    System.out.println("Задача с id: " + task.getId() + " успешно добавлена в очередь потоком: " + Thread.currentThread().getName() + "\n" + "Количество задач в очереди: " + queue.size());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            allTaskAdded.set(true);
            producerService.shutdown();
        });

        consumerService.execute(() -> {
            while (!queue.isEmpty()) {
                try {
                    Task task = queue.take();
                    System.out.println("Задача с id: " + task.getId() + " успешно получена" + "\n" + "Количество задач в очереди: " + queue.size());
                    System.out.println("Поток: " + Thread.currentThread().getName() + " приступил к выполнению задачи");
                    task.work();
                    System.out.println("Поток: " + Thread.currentThread().getName() + " завершил выполнение задачи задачи");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            consumerService.shutdown();
        });

        try {
            producerService.awaitTermination(1, TimeUnit.HOURS);
            consumerService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Все задачи выполнены");
        System.out.println("Задач в очереди: " + queue.size());

    }
}