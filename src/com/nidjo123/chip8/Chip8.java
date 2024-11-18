package com.nidjo123.chip8;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Chip8 {
    private Memory memory = new Memory();
    private int programCounter = 0x200;
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

    private short fetchInstruction() {
        int instruction = Byte.toUnsignedInt(memory.getByte(this.programCounter)) << 8;
        instruction |= Byte.toUnsignedInt(memory.getByte(this.programCounter + 1));
        this.programCounter += 2;
        return (short) (instruction & 0xFFFF);
    }

    private void decodeExecute(short instruction) {
        int opCode = (instruction & 0xF000) >> 12;
        int registerX = (instruction & 0x0F00) >> 8;
        int registerY = (instruction & 0x00F0) >> 4;
        int immediate4 = instruction & 0x000F;
        int immediate8 = instruction & 0x00FF;
        int immediate12 = instruction & 0x0FFF;

        if (instruction == 0x00E0) {
            display.clear();
        } else if (opCode == 0x1) {
            this.programCounter = immediate12;
        } else if (opCode == 0x6) {
            registers[registerX] = (byte) immediate8;
        } else if (opCode == 0x7) {
            registers[registerX] += (byte) immediate8;
        } else if (opCode == 0xA) {
            indexRegister = immediate12;
        } else if (opCode == 0xD) {
            registers[0xF] = 0;
            int startX = registers[registerX];
            int startY = registers[registerY];
            for (int y = 0; y < immediate4; y++) {
                byte spriteByte = memory.getByte(indexRegister + y);
                for (int x = 7; x >= 0; x--) {
                    if (((spriteByte >> x) & 0x1) != 0) {
                        int xPos = startX + 7 - x;
                        int yPos = startY + y;
                        display.togglePixel(xPos, yPos);
                        if (!display.getPixel(xPos, yPos)) {
                            registers[0xF] = 1;
                        }
                    }
                }
            }
        }
    }

    public void tick() {
        short instruction = fetchInstruction();
        decodeExecute(instruction);
    }

    public boolean[] getDisplayPixels() {
        boolean[] pixels = display.getPixels();
        return Arrays.copyOf(pixels, pixels.length);
    }
}
