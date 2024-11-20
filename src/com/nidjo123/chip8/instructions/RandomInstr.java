package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

import java.util.Random;

public class RandomInstr extends Instruction {
    private static final Random random = new Random();
    private static final byte[] bytes = new byte[1];

    public RandomInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        random.nextBytes(bytes);
        context.registers[this.registerX] = (byte) (bytes[0] & this.immediate8);
    }
}
