package com.example.flashcards.exceptions;

/**
 * Thrown to indicate that Bundle for
 * activity is not appropriate.
 */
public class IllegalBundleException extends Exception {
    public IllegalBundleException(String message) {
        super(message);
    }
}
