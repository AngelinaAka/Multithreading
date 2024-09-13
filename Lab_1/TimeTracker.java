//zadacha_3
public class TimeTracker {
    public static void main(String[] args) {
        // Создаем поток-хронометр
        Thread timeTracker = new Thread(new TimeTrackerRunnable());
        timeTracker.start();

        // Создаем поток, выводит сообщение каждые 5 секунд
        Thread messageThread = new Thread(new MessageThreadRunnable());
        messageThread.start();

        // Создаем поток, выводит сообщение каждые 7 секунд
        Thread anotherMessageThread = new Thread(new AnotherMessageThreadRunnable());
        anotherMessageThread.start();
    }
}
