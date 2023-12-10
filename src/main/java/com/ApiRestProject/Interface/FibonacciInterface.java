package com.ApiRestProject.Interface;

import com.ApiRestProject.Exception.LowerLimitException;

import java.util.List;
import java.util.Map;

public interface FibonacciInterface {

    int generate(String client);
    int stepBack(String client) throws LowerLimitException;
    List<Integer> getFiboList(String client);
}
