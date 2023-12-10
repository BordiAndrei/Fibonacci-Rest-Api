package com.ApiRestProject.Exception;

public class ClientNotFoundException extends Exception {

    public ClientNotFoundException() {
        super("Client not found");
    }

}
