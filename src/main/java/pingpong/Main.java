package pingpong;

public class Main {
    public static void main(String[] args) {
        PingPongThread pingThread = new PingPongThread("PING");
        PingPongThread pongThread = new PingPongThread("pong");
        pingThread.start();
        pongThread.start();
    }
}
