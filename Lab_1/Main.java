public class Main {
    public static void main(String[] args) {
        MyThread threadExtendingThread = new MyThread();
        threadExtendingThread.start();

        MyRunnable runnableExtendingRunnable = new MyRunnable();
        Thread threadRunnable = new Thread(runnableExtendingRunnable);
        threadRunnable.start();
    }
}
