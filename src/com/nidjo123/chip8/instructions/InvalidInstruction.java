package com.nidjo123.chip8.instructions;

public class InvalidInstruction extends RuntimeException {
    public InvalidInstruction(String message) {
        super(message);
    }


}
