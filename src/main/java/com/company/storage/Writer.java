package com.company.storage;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Writer implements Runnable {

    private final Storage storage;

    public Writer(Storage storage) {
        this.storage = storage;
    }

    @Override
    @SneakyThrows
    public void run() {
        for (int i = 0; i < 5; ++i) {
            storage.setString(String.valueOf(i));
            System.out.println("Я написал ветра зимы!!!"+String.valueOf(i));
            TimeUnit.SECONDS.sleep(3);
        }
    }
}