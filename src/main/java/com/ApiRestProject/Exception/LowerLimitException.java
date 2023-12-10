package com.ApiRestProject.Exception;

public class LowerLimitException extends  Exception {

    public LowerLimitException() {
        super("Lower limit reached!");
    }

}
