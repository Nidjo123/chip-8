package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class LoadRegistersInstr extends Instruction {
    public LoadRegistersInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        int numRegisters = this.registerX + 1;
        for (int i = 0; i < numRegisters; i++) {
            byte value = context.memory.getByte(context.indexRegister + i);
            context.registers[i] = value;
        }

    }
}
