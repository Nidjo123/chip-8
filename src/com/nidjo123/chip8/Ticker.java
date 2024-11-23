package com.nidjo123.chip8;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Ticker {
    public interface OnTick {
        void onTick();
    }

    private long tickCount;
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
        long ticks = sinceLastTick.dividedBy(durationBetweenTicks);
        if (ticks > 0) {
            Duration leftover = sinceLastTick.minus(durationBetweenTicks.multipliedBy(ticks));
            lastInstant = now.minus(leftover);
        }
        for (long i = 0; i < ticks; i++) {
            for (OnTick callback : callbacks) {
                callback.onTick();
            }
        }
        tickCount += ticks;
    }

    public void addCallback(OnTick callback) {
        callbacks.add(callback);
    }

    public long getTickCount() {
        return tickCount;
    }

    public void resetTickCount() {
        this.tickCount = 0;
    }
}
