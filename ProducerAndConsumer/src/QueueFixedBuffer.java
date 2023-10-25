import java.util.LinkedList;
import java.util.Queue;

public class QueueFixedBuffer<T> {

    private final Queue<T> queue;

    private final int buffer;

    public QueueFixedBuffer(int buffer) {
        this.queue = new LinkedList<>();
        this.buffer = buffer;
    }

    public int getBuffer() {
        return this.buffer;
    }

    public int size() {
        return queue.size();
    }

    public synchronized T pool() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.fillInStackTrace();
            }
        }
        T t = queue.poll();
        notifyAll();
        return t;
    }

    public synchronized boolean offer(T t) {

        while (size() >= buffer) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.fillInStackTrace();
            }
        }
        boolean b = queue.offer(t);
        notifyAll();
        return b;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
