package com.ApiRestProject.Service;

import com.ApiRestProject.Exception.LowerLimitException;
import com.ApiRestProject.Interface.FibonacciInterface;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FibonacciService implements FibonacciInterface {

    private static final Map<String, Integer> CLIENT_ITERATION = new ConcurrentHashMap<>();
    private static final Map<Integer, Integer> ITERATION_FIBO_NUMBER = new ConcurrentHashMap<Integer, Integer>();

    public int generate(String client) {

        if (!CLIENT_ITERATION.containsKey(client)) {
            CLIENT_ITERATION.computeIfAbsent(client, (k) -> 1);
        }else{
            CLIENT_ITERATION.compute(client, (k, currenIteration) -> currenIteration + 1);
        }

        return getFibonacciNumber(CLIENT_ITERATION.get(client));


        /* my second option

        if (!CLIENT_ITERATION.containsKey(client)) {
            init(client);
        } else {
            CLIENT_ITERATION.put(client,CLIENT_ITERATION.get(client) + 1);
        }

        return getFibonacciNumber(CLIENT_ITERATION.get(client));*/
    }

    public Integer getCurrentIteration(String client) {
        return CLIENT_ITERATION.get(client);
    }

    public List<Integer> getFiboList(String client) {

        return IntStream.range(1, getCurrentIteration(client) + 1)
                .mapToObj(this::getFibonacciNumber)
                .collect(Collectors.toList());


       /*

       my option

       final List<Integer> fiboList = new ArrayList<Integer>();

        final int currentIteration = getCurrentIteration(client);

        for (int i = 1; i <= currentIteration; i++) {
            fiboList.add(getFibonacciNumber(i));
        }

        return fiboList;

        */
    }



    static {
        ITERATION_FIBO_NUMBER.put(1,1);
        ITERATION_FIBO_NUMBER.put(2,1);
    }

    private int getFibonacciNumber(int iteration) {

        return ITERATION_FIBO_NUMBER.computeIfAbsent(iteration, (k)
                -> ITERATION_FIBO_NUMBER.get(iteration - 1) + ITERATION_FIBO_NUMBER.get(iteration - 2));


       /*
        my second option

        if (n <= 2) {
            return 1;
        }*/

        // Memoization Tabulare - save time and memory for getFiboList
       /* if (!ITERATION_FIBO_NUMBER.containsKey(n)) {

            int fiboToReturn = 0;

            int fibo1 = 1;
            int fibo2 = 1;

            for (int i = 2; i < n; i++) {
                fiboToReturn = fibo1 + fibo2;
                fibo1 = fibo2;
                fibo2 = fiboToReturn;
            }

            System.out.println("currentIteration: " + n);
            ITERATION_FIBO_NUMBER.put(n, fiboToReturn);

        }*/

       /* return ITERATION_FIBO_NUMBER.get(n);*/
    }

    private void init(String client) {
        CLIENT_ITERATION.put(client, 1);
    }

    public int stepBack(String client) throws LowerLimitException {

        final int currentIteration = getCurrentIteration(client);

        if (currentIteration == 1) {
            throw new LowerLimitException();
        } else {
            CLIENT_ITERATION.put(client, currentIteration - 1);
        }

        return getCurrentIteration(client);

    }
}
