package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class ShrVXInstr extends Instruction {
    public ShrVXInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        // TODO: make this configurable (first set VX <- VY and then proceed)
        byte xValue = context.registers[this.registerX];
        context.registers[0xF] = (byte) (xValue & 0x1);
        context.registers[this.registerX] >>>= 1;
    }
}
