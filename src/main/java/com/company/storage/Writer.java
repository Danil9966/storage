package com.company.storage;

import java.util.concurrent.TimeUnit;

public class Writer implements Runnable {

    private final Storage storage;

    public Writer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; ++i) {
            try {
                storage.setString(String.valueOf(i));
                System.out.println("Я написал ветра зимы!!!"+String.valueOf(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}