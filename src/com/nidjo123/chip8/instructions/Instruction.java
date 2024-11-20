package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public abstract class Instruction {
    protected int opCode;
    protected int registerX;
    protected int registerY;
    protected int immediate4;
    protected int immediate8;
    protected int immediate12;

    public Instruction(short word) {
        opCode = extractOpCode(word);
        registerX = (word & 0x0F00) >> 8;
        registerY = (word & 0x00F0) >> 4;
        immediate4 = word & 0x000F;
        immediate8 = word & 0x00FF;
        immediate12 = word & 0x0FFF;
    }

    public static int extractOpCode(short word) {
        return (word & 0xF000) >> 12;
    }

    public abstract void execute(Chip8.Context context);
}
