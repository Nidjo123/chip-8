package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class SkipVXImmNotEqInstr extends Instruction {
    public SkipVXImmNotEqInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        byte xValue = context.registers[this.registerX];
        if (xValue != this.immediate8) {
            context.programCounter += 2;
        }
    }
}
