package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class BinaryAndInstr extends Instruction {
    public BinaryAndInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        context.registers[this.registerX] &= context.registers[this.registerY];
    }
}
