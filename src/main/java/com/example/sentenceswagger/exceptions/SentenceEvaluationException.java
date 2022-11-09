package com.example.sentenceswagger.exceptions;

public class SentenceEvaluationException extends RuntimeException{
    String customMessage;
    public SentenceEvaluationException(String customMessage) {
        super(customMessage);
        this.customMessage=customMessage;
    }

}
