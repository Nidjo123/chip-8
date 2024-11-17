package com.nidjo123.chip8;

public class Timer {
    private int time;

    public void tick() {
        if (time > 0) {
            time--;
        }
    }
}
