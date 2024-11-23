package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class SubVXVYInstr extends Instruction {
    public SubVXVYInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        byte xValue = context.registers[this.registerX];
        byte yValue = context.registers[this.registerY];
        context.registers[0xF] = (byte) (xValue > yValue ? 1 : 0);
        context.registers[this.registerX] = (byte) (xValue - yValue);
    }
}
