package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class ClearScreenInstr extends Instruction {
    public ClearScreenInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        context.display.clear();
    }
}
