public class MyThread extends Thread {
    private static final MyRunnable runnableExtendingRunnable = new MyRunnable();

    public MyThread() {
        super(runnableExtendingRunnable);
    }
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 10 == 0) {
                System.out.println("Thread: " + i);
                try {
                    Thread.sleep(1000); // Пауза в 1 секунду
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
