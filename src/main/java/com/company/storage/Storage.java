package com.company.storage;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Storage {

    private volatile String string = "DEAFAULT";

    private volatile int numOfReaders = 0;
    @SneakyThrows
     public void setString(String newValue)  {
       {
           synchronized (string) {
               TimeUnit.SECONDS.sleep(1);
               string = newValue;
           }
        }
    }
    @SneakyThrows
     public String getString() {

           TimeUnit.SECONDS.sleep(1);
           return string;

        }

    }
