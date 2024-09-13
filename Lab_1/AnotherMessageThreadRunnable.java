//zadacha_3
public class AnotherMessageThreadRunnable implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("Другое сообщение из потока!");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                System.err.println("Поток с другим сообщением прерван: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}