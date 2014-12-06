package com.store.exception;

public class PDFFileCreationException extends RuntimeException {

    /**
     */
    private static final long serialVersionUID = -1042299833149386257L;

    public PDFFileCreationException() {
        super();
    }
    
    public PDFFileCreationException(String message) {
        super(message);
    }
    
}
