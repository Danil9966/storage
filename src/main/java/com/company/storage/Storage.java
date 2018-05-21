package com.company.storage;

import java.util.concurrent.TimeUnit;

public class Storage {

    private volatile String string = "DEAFAULT";

     public void setString(String newValue) throws InterruptedException {
       {
           synchronized (string) {

               TimeUnit.SECONDS.sleep(1);
               string = newValue;
           }
        }
    }

     public String getString() throws InterruptedException {
       {
           synchronized (string) {
               TimeUnit.SECONDS.sleep(1);
               return string;
           }
        }

    }
}