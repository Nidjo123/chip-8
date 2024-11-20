package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class CallInstr extends Instruction {
    public CallInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        context.addressStack.push(context.programCounter);
        context.programCounter = this.immediate12;
    }
}
