package com.company.storage;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Reader implements Runnable {

    private final Storage storage;

    public Reader(Storage storage) {
        this.storage = storage;
    }

    @Override
    @SneakyThrows
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 15; ++i) {
            System.out.println(threadName + " - " + i + " - " + storage.getString());
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}