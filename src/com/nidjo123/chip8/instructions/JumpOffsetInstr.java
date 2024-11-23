package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class JumpOffsetInstr extends Instruction {
    public JumpOffsetInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        // TODO: Make offset configurable (either V0 or VX)
        byte offset = context.registers[0x0];
        context.programCounter = this.immediate12 + offset;
    }
}
