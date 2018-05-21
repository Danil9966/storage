package com.company.storage;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Launcher {

    @SneakyThrows
    public static void main(String[] args) {
        Storage storage = new Storage();

        Writer writer = new Writer(storage);
        Thread writerThread = new Thread(writer);
        writerThread.setPriority(Thread.MAX_PRIORITY);
        writerThread.start();

        Thread[] readers = new Thread[4];
        for (int i = 0; i < readers.length; ++i) {
            readers[i] = new Thread(new Reader(storage));
        }
       // TimeUnit.SECONDS.sleep(2L);
        for (Thread reader : readers) {
            reader.start();
        }
    }
}