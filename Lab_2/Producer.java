import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                // Генерация данных (в данном случае - чисел)
                int data = count++;
                // Помещение данных в буфер
                queue.put(data);
                System.out.println("Producer produced: " + data);
                // Дополнительная логика производителя, например, задержка
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


