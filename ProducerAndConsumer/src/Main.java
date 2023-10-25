import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public final static int SIZE_BUFFER = 10;
    public final static int COUNT_PRODUCER = 2;
    public final static int COUNT_CONSUMER = 4;

    public static void main(String[] args) {

        QueueFixedBuffer<Task> queue = new QueueFixedBuffer<>(SIZE_BUFFER);

        try (ExecutorService producer = Executors.newFixedThreadPool(COUNT_PRODUCER);
             ExecutorService consumer = Executors.newFixedThreadPool(COUNT_CONSUMER)) {

            for (int i = 0; i < COUNT_PRODUCER; i++) {
                producer.execute(new Producer(queue));
            }

            for (int i = 0; i < COUNT_CONSUMER; i++) {
                consumer.execute(new Consumer(queue));
            }


//            producer.shutdown();
//            consumer.shutdown();
//
//            System.out.println("\n----Потоки производители завершили работу: " + producer.awaitTermination(1, TimeUnit.HOURS) + "\n");
//            System.out.println("\n----Потоки потребители завершили работу: " +consumer.awaitTermination(1, TimeUnit.HOURS) + "\n");
//        } catch (InterruptedException iEx) {
//            System.out.println("Возникла ошибка при работе с потоками");
        }
    }
}