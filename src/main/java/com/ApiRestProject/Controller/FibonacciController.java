package com.ApiRestProject.Controller;

import com.ApiRestProject.Entity.FibonacciRequest;
import com.ApiRestProject.Entity.FibonacciResponse;
import com.ApiRestProject.Exception.FibonacciException;
import com.ApiRestProject.Exception.LowerLimitException;
import com.ApiRestProject.Service.FibonacciService;
import com.ApiRestProject.Validation.FibonacciValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class FibonacciController {
    private final FibonacciService fibonacciService;
    private final FibonacciValidator fibonacciValidator;

    @Autowired
    public FibonacciController(FibonacciService fibonacciService, FibonacciValidator fibonacciValidator) {
        this.fibonacciService = fibonacciService;
        this.fibonacciValidator = fibonacciValidator;
    }

    @PostMapping("/generate")
    public FibonacciResponse generate(@RequestBody FibonacciRequest fibonacciRequest) {

        try {
            fibonacciValidator.validateRequest(fibonacciRequest);
        } catch (FibonacciException e){
            return new FibonacciResponse(e.getMessage());
        }

        int fibonacciNumber = fibonacciService.generate(fibonacciRequest.getClient());
        int currentIteration = fibonacciService.getCurrentIteration(fibonacciRequest.getClient());
        return new FibonacciResponse(fibonacciRequest.getClient(), currentIteration, fibonacciNumber);
    }

    @GetMapping("/list")
    public FibonacciResponse generatedList(@RequestBody FibonacciRequest fibonacciRequest) {

        try {
            fibonacciValidator.validateRequest(fibonacciRequest);
            fibonacciValidator.validateClient(fibonacciRequest.getClient());
        } catch (FibonacciException e){
            return new FibonacciResponse(e.getMessage());
        }

        List<Integer> fiboList = fibonacciService.getFiboList(fibonacciRequest.getClient());
        return new FibonacciResponse(fibonacciRequest.getClient(), fiboList);
    }

    @PostMapping("/stepBack")
    public FibonacciResponse stepBack(@RequestBody FibonacciRequest fibonacciRequest) {

        try {
            fibonacciValidator.validateRequest(fibonacciRequest);
            fibonacciValidator.validateClient(fibonacciRequest.getClient());
            final int previousIteration = fibonacciService.stepBack(fibonacciRequest.getClient());
            return new FibonacciResponse(fibonacciRequest.getClient(), previousIteration);

        } catch (LowerLimitException e) {
            return new FibonacciResponse(e.getMessage());
        } catch (FibonacciException e){
            return new FibonacciResponse(e.getMessage());
        }

    }
}
