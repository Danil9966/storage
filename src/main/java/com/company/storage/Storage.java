package com.company.storage;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Storage {

    private volatile String string = "DEFAULT";
    private volatile int numOfReader = 0;
    private final Object SYNCHRO = new Object();
    @SneakyThrows
     public void setString(String newValue)  {
        synchronized (SYNCHRO) {
            while (isReadersPresent())
                SYNCHRO.wait();
           TimeUnit.SECONDS.sleep(1);
           string = newValue;
        }
    }

    @SneakyThrows
     public String getString() {
        incNumOfReaders();
        TimeUnit.SECONDS.sleep(1);
        try {
            return string;
        }
        finally {
            decNumOfReaders();
            if(numOfReader == 0) {
                synchronized (SYNCHRO) {
                    SYNCHRO.notify();
                }
            }
        }
    }

    void incNumOfReaders(){
        synchronized (SYNCHRO) {
            numOfReader++;
        }
    }

    void decNumOfReaders(){
        synchronized (SYNCHRO) {
            numOfReader--;
        }
    }

    boolean isReadersPresent(){
        return numOfReader > 0;
    }
}
