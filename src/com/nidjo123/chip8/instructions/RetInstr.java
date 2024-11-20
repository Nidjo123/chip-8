package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class RetInstr extends Instruction {
    public RetInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        context.programCounter = context.addressStack.pop();
    }
}
