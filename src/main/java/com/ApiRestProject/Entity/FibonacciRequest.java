package com.ApiRestProject.Entity;

public class FibonacciRequest {
    private String client;


    public FibonacciRequest() {
    }

    public FibonacciRequest(String client) {
        this.client = client;

    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }



    @Override
    public String toString() {
        return "FibonacciRequest{" +
                "client='" + client + '\'' +
                '}';
    }
}
