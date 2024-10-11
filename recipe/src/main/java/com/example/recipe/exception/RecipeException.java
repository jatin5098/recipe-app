package com.example.recipe.exception;

public class RecipeException extends RuntimeException {
    public RecipeException() {
    }

    public RecipeException(String message) {
        super(message);
    }

    public RecipeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecipeException(Throwable cause) {
        super(cause);
    }

    public RecipeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
