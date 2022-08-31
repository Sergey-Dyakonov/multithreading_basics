package consumerProducer;

import lombok.SneakyThrows;

import java.util.LinkedList;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        final SharedSrc pc = new SharedSrc();

        Thread producer = new Thread(() -> pc.produce());
        Thread consumer = new Thread(() -> pc.consume());

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

    public static class SharedSrc {
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = 2;

        @SneakyThrows
        public void produce() {
            int value = 0;
            while (true) {
                synchronized (this) {
                    while (list.size() == capacity) wait();
                    System.out.println("Producer produced-" + value);
                    list.add(value++);
                    notify();
                }
            }
        }

        @SneakyThrows
        public void consume() {
            while (true) {
                synchronized (this) {
                    while (list.size() == 0) wait();
                    int val = list.removeFirst();
                    System.out.println("Consumer consumed-" + val);
                    notify();
                    Thread.sleep(1000);
                }
            }
        }
    }
}