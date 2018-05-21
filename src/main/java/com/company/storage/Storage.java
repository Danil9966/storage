package com.company.storage;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Storage {

    private volatile String string = "DEAFAULT";
    private volatile Integer numOfReader= 0;
    private final Object SYNCHRO =new Object();
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
        numOfReader++;
        TimeUnit.SECONDS.sleep(1);
        numOfReader--;
        synchronized (SYNCHRO) {
            SYNCHRO.notify();
        }
        return string;

    }


    boolean isReadersPresent(){
        return numOfReader>0;
    }
}
