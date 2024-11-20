package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class JumpInstr extends Instruction {
    public JumpInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        context.programCounter = this.immediate12;
    }
}
