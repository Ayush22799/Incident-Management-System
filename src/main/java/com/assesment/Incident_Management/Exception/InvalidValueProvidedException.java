package com.assesment.Incident_Management.Exception;

public class InvalidValueProvidedException extends RuntimeException{
    String message;
    public InvalidValueProvidedException(String message){
        super(message);
        this.message = message;
    }
}
