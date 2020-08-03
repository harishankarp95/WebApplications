package com.hari.app.exception;

public class AppUserBlockedException extends Exception{

	/**
     * Creates User object without error description.
     */
    public AppUserBlockedException() {
    }
    /**
     * Creates User object with error description.
     */
    public AppUserBlockedException(String errDesc) {
        super(errDesc);
    }    
}
