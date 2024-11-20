package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class SetIndexInstr extends Instruction {
    public SetIndexInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        context.indexRegister = this.immediate12;
    }
}
