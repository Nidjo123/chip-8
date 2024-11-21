package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class SkipVXVYNotEq extends Instruction {
    public SkipVXVYNotEq(short word) {
        super(word);
        if (this.immediate4 != 0) {
            throw new InvalidInstruction("Unexpected last byte: " + Integer.toHexString(this.immediate4));
        }
    }

    @Override
    public void execute(Chip8.Context context) {
        byte xValue = context.registers[this.registerX];
        byte yValue = context.registers[this.registerY];
        if (xValue != yValue) {
            context.programCounter += 2;
        }
    }
}
