package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class StoreRegistersInstr extends Instruction {
    public StoreRegistersInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        int numRegisters = this.registerX + 1;
        byte[] registerValues = new byte[numRegisters];
        System.arraycopy(context.registers, 0, registerValues, 0, numRegisters);
        context.memory.setBytes(registerValues, context.indexRegister);
    }
}
