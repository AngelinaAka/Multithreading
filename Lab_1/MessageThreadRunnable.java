//zadacha_3
public class MessageThreadRunnable implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("Сообщение из побочного потока!");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.err.println("Поток с сообщением прерван: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
