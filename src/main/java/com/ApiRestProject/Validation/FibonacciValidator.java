package com.ApiRestProject.Validation;

import com.ApiRestProject.Entity.FibonacciRequest;
import com.ApiRestProject.Exception.FibonacciException;
import com.ApiRestProject.Service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FibonacciValidator {

    @Autowired
    private FibonacciService fibonacciService;

    public void validateRequest(FibonacciRequest fibonacciRequest) throws FibonacciException {
        if(fibonacciRequest == null){
            throw new FibonacciException("Blank Request!");
        }

        String client = fibonacciRequest.getClient();
        if(client == null || client.trim().isEmpty()){
            throw new FibonacciException("Client not present");
        }
    }

    public void validateClient(String client) throws FibonacciException{

        if(fibonacciService.getCurrentIteration(client) == null){
            throw new FibonacciException("Client not found!");
        }

    }


}
