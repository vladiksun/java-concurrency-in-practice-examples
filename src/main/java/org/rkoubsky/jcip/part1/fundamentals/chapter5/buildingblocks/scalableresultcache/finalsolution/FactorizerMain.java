package org.rkoubsky.jcip.part1.fundamentals.chapter5.buildingblocks.scalableresultcache.finalsolution;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FactorizerMain {

    public static void main(String[] args) throws Exception {
        //create a memoizer that performs factorials
        final FinalMemoizer<Integer, Integer> memo = new FinalMemoizer<>(a -> {
            int result = 1;
            for (int i = 1; i < a; i++) {
                result = result * i;
            }
            return result;
        });

        //now call the memoizer
        System.out.println(memo.compute(10));


        //call it with 10 threads concurrently
        ExecutorService exec = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<Integer> compService = new ExecutorCompletionService<>(exec);

        for (int i = 0; i < 15; i++) {
            compService.submit(() -> memo.compute(5));
        }

        exec.shutdown();

        for (int i = 0; i < 15; i++) {
            System.out.println(compService.take().get());
        }
    }
}
