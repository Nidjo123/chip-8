package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class AddVXToIInstr extends Instruction {
    public AddVXToIInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        context.indexRegister += context.registers[this.registerX];
        if (context.indexRegister >= 0x1000) {
            context.registers[0xF] = 1;
        }
    }
}
