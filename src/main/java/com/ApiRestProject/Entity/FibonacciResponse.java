package com.ApiRestProject.Entity;

import java.util.List;

public class FibonacciResponse {

    private static final String ARROW = " -> ";
    private String response;

    public FibonacciResponse(String client, int currentIteration, int fiboNumber) {
        final StringBuilder sb = new StringBuilder(client);
        sb.append(ARROW)
                .append(" apel generare ")
                .append(currentIteration)
                .append(ARROW)
                .append("rezultat: ")
                .append(fiboNumber);
        this.response = sb.toString();
    }

    public FibonacciResponse(String client, int previousIteration) {
        final StringBuilder sb = new StringBuilder(client);
        sb.append(ARROW)
                .append("apel pas inapoi ")
                .append(previousIteration)
                .append(ARROW)
                .append("rezultat: OK");
        this.response = sb.toString();
    }

    public FibonacciResponse(String client, List<Integer> fiboList) {
        final StringBuilder sb = new StringBuilder(client);
        sb.append(ARROW)
                .append("apel listare valori generate")
                .append(ARROW)
                .append("rezultat: ")
                .append(fiboList.toString());
        this.response = sb.toString();
    }

    public FibonacciResponse(String errorMsg) {
        response = errorMsg;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
