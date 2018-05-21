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
        String result = new String(this.string) ;
        incNumOfReaders();
        TimeUnit.SECONDS.sleep(1);
        decNumOfReaders();
        synchronized (SYNCHRO) {
            SYNCHRO.notifyAll();
        }
        return result;

    }
    synchronized void incNumOfReaders(){
        numOfReader++;

    }
    synchronized void decNumOfReaders(){
        numOfReader--;

    }

    boolean isReadersPresent(){
        return numOfReader>0;
    }
}
