package com.duszynski.tracker_tkd.exception;

public class TrainingSessionNotFoundException extends RuntimeException {
    public TrainingSessionNotFoundException(String message) {
        super(message);
    }
}
