package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class VXToSoundTimerInstr extends Instruction {
    public VXToSoundTimerInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        byte xValue = context.registers[this.registerX];
        context.soundTimer.setTime(xValue);
    }
}
