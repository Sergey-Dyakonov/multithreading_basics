package pingpong;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

public class PingPongThread extends Thread {
    private final String msg;
    private static String turn;

    public PingPongThread(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        while (true) {
            execute();
        }
    }

    @SneakyThrows
    public synchronized void execute() {
        if (!msg.equals(turn)) {
            turn = msg;
            sleep(700);
            System.out.println(msg);
        }
    }
}