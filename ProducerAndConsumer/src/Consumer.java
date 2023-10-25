public class Consumer implements Runnable {

    private final QueueFixedBuffer<Task> queue;

    public Consumer(QueueFixedBuffer<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Task task = queue.pool();
            System.out.println("--Поток: " + Thread.currentThread().getName() + " получил задачу с id: " + task.getId());
            System.out.println("Осталось задач: " + queue.size());
            task.work();
        }
    }
}
