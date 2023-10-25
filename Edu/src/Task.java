/**
 * @author mrGreenNV
 */
public class Task {
    private static int count;

    private final int id;

    public Task() {
        count++;
        this.id = count;
    }

    public int getId() {
        return id;
    }

    public void work() {
        System.out.println("Задача с id: " + this.getId() + " начинает работу");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Задача с id: " + this.getId() + " завершила работу");
    }
}
