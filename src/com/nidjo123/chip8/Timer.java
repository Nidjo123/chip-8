package com.nidjo123.chip8;

public class Timer {
    private byte time;

    public void tick() {
        if (time > 0) {
            time--;
        }
    }

    public void setTime(byte time) {
        this.time = time;
    }

    public byte getTime() {
        return time;
    }
}
