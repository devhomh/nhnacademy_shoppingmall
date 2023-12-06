package com.nhnacademy.shoppingmall.data.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String userId){
        super(String.format("user already exist:%s",userId));
    }
}
