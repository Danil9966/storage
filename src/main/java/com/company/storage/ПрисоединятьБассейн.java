package com.company.storage;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class ПрисоединятьБассейн {

        public static void main(String[] args) {
            int[] data = ThreadLocalRandom.current().ints(15, 0, 5).toArray();

            ForkJoinPool pool = ForkJoinPool.commonPool();
            for(int i = 0; i<data.length;i++)
                System.out.println(data[i]+";");

            pool.invoke(new ArraySum(data, 0, data.length - 1));

            System.out.println("After: " + ArraySum.RESULT);
        }

        @AllArgsConstructor
        private static class ArraySum extends RecursiveTask<Integer> {

            private static final int SEQUENTIAL_THRESHOLD = 10;
            private int[] data;
            private int fromInclusive;
            private int toInclusive;
            static volatile int RESULT= 0;

            @Override
            protected Integer compute() {

                if (toInclusive - fromInclusive < SEQUENTIAL_THRESHOLD) {
                    return sum(data, fromInclusive, toInclusive );
                }
                else{
                    ArraySum left = new ArraySum(data, fromInclusive, (toInclusive-fromInclusive)/2);
                    ArraySum right = new ArraySum(data, (toInclusive-fromInclusive)/2 + 1, toInclusive);
                    ForkJoinTask.invokeAll(left,right);

                }
                return null;
            }

            private static int sum(int[] array, int fromInclusive, int toInclusive) {
                int result = 0;
                for(int i = fromInclusive; i<=toInclusive; i++){
                    result+= array[i];
                }
                RESULT+=result;
                return result;
            }
        }



}
