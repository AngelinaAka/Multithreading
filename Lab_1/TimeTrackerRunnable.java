//zadacha_3
public class TimeTrackerRunnable implements Runnable {
    private long startTime = System.currentTimeMillis();
    @Override
    public void run() {
        while (true) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Прошло времени: " + elapsedTime / 1000 + " секунд");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Поток хронометра прерван: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
