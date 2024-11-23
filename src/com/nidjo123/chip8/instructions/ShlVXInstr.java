package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class ShlVXInstr extends Instruction {
    public ShlVXInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        byte xValue = context.registers[this.registerX];
        context.registers[0xF] = (byte) ((xValue >> 7) & 0x1);
        context.registers[this.registerX] <<= 1;
    }
}
