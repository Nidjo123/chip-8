package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class DecimalDigitsInstr extends Instruction {
    public DecimalDigitsInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        int xValue = Byte.toUnsignedInt(context.registers[this.registerX]);
        byte[] digits = {(byte) (xValue / 100), (byte) (xValue % 100 / 10), (byte) (xValue % 10)};
        context.memory.setBytes(digits, context.indexRegister);
    }
}
