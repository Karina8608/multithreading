public class PingPongGame {
    private static final int MAX_TURNS = 10;
    private static final Object lock = new Object();
    private static boolean isPingTurn = true;

    public static void main(String[] args) {
        Thread pingThread = new Thread(PingPongGame::ping);
        Thread pongThread = new Thread(PingPongGame::pong);

        pingThread.start();
        pongThread.start();
    }

    private static void ping() {
        synchronized (lock) {
            try {
                for (int i = 0; i < MAX_TURNS; i++) {
                    while (!isPingTurn) {
                        lock.wait();
                    }
                    System.out.println("Пинг");
                    isPingTurn = false;
                    lock.notify();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void pong() {
        synchronized (lock) {
            try {
                for (int i = 0; i < MAX_TURNS; i++) {
                    while (isPingTurn) {
                        lock.wait();
                    }
                    System.out.println("Понг");
                    isPingTurn = true;
                    lock.notify();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

