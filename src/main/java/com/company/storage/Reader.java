package com.company.storage;

import java.util.concurrent.TimeUnit;

public class Reader implements Runnable {

    private final Storage storage;

    public Reader(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 10; ++i) {
            try {
                System.out.println(threadName + " - " + i + " - " + storage.getString());
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}