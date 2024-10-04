import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                // Извлечение данных из буфера
                int data = queue.take();
                System.out.println("Consumer consumed: " + data);
                // Дополнительная логика потребителя, например, обработка данных
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
