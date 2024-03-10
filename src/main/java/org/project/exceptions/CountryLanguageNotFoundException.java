package org.project.exceptions;

public class CountryLanguageNotFoundException extends RuntimeException{
    public CountryLanguageNotFoundException(String message) {
        super(message);
    }
}
