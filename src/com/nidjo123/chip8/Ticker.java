package com.nidjo123.chip8;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Ticker {
    public interface OnTick {
        void onTick();
    }

    private final Duration durationBetweenTicks;
    private Instant lastInstant;
    private List<OnTick> callbacks = new ArrayList<>();

    public Ticker(int ticksPerSecond) {
        durationBetweenTicks = Duration.ofNanos((long) (1. / ticksPerSecond * 1e9));
        lastInstant = Instant.now();
    }

    public void update() {
        Instant now = Instant.now();
        Duration sinceLastTick = Duration.between(lastInstant, now);
        while (sinceLastTick.dividedBy(durationBetweenTicks) >= 1) {
            sinceLastTick = sinceLastTick.minus(durationBetweenTicks);
            for (OnTick callback : callbacks) {
                callback.onTick();
            }
            lastInstant = now;
        }
    }

    public void addCallback(OnTick callback) {
        callbacks.add(callback);
    }
}
