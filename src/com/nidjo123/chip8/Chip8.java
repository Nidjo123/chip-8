package com.nidjo123.chip8;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Chip8 {
    private Memory memory = new Memory();
    private int programCounter;
    private int indexRegister;
    private Stack<Integer> addressStack = new Stack<>();
    private byte[] registers = new byte[16];
    private Timer delayTimer = new Timer();
    private Timer soundTimer = new Timer();
    private Display display;

    public Chip8(int width, int height) {
        display = new Display(width, height);
    }

    public void loadRom(Rom rom) {
        loadRom(rom, 0x200);
    }

    public void loadRom(Rom rom, int startAddress) {
        if (memory.getSizeBytes() - startAddress < rom.getSize()) {
            throw new RuntimeException("Rom doesn't fit into memory");
        }
        byte[] romBytes = rom.getContent();
        memory.setBytes(romBytes, startAddress);
    }

    public void tickTimers() {
        delayTimer.tick();
        soundTimer.tick();
    }

    public void tick() {
        Random random = new Random();
        for (int y = 0; y < display.getHeight(); y++) {
            for (int x = 0; x < display.getWidth(); x++) {
                display.setPixel(x, y, random.nextBoolean());
            }
        }
    }

    public boolean[] getDisplayPixels() {
        boolean[] pixels = display.getPixels();
        return Arrays.copyOf(pixels, pixels.length);
    }
}
