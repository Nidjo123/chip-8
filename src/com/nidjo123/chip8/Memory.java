package com.nidjo123.chip8;

public class Memory {
    private byte[] memory = new byte[4096];

    private static final short[] FONT = {
            0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
            0x20, 0x60, 0x20, 0x20, 0x70, // 1
            0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
            0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
            0x90, 0x90, 0xF0, 0x10, 0x10, // 4
            0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
            0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
            0xF0, 0x10, 0x20, 0x40, 0x40, // 7
            0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
            0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
            0xF0, 0x90, 0xF0, 0x90, 0x90, // A
            0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
            0xF0, 0x80, 0x80, 0x80, 0xF0, // C
            0xE0, 0x90, 0x90, 0x90, 0xE0, // D
            0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
            0xF0, 0x80, 0xF0, 0x80, 0x80  // F
    };
    private static final int FONT_ADDRESS = 0x50;

    public Memory() {
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < FONT.length; i++) {
            memory[FONT_ADDRESS + i] = (byte) FONT[i];
        }
    }

    public int getSizeBytes() {
        return memory.length;
    }

    public void setBytes(byte[] bytes, int startAddress) {
        assert memory.length - startAddress >= bytes.length;
        System.arraycopy(bytes, 0, memory, startAddress, bytes.length);
    }

    public byte getByte(int address) {
        return memory[address];
    }

    public int getFontCharacterAddress(byte hexChar) {
        return FONT_ADDRESS + (hexChar * FONT.length / 16);
    }
}
