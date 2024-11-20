package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class DrawInstr extends Instruction {
    public DrawInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        context.registers[0xF] = 0;
        int startX = context.registers[registerX];
        int startY = context.registers[registerY];
        for (int y = 0; y < immediate4; y++) {
            byte spriteByte = context.memory.getByte(context.indexRegister + y);
            for (int x = 7; x >= 0; x--) {
                if (((spriteByte >> x) & 0x1) != 0) {
                    int xPos = startX + 7 - x;
                    int yPos = startY + y;
                    context.display.togglePixel(xPos, yPos);
                    if (!context.display.getPixel(xPos, yPos)) {
                        context.registers[0xF] = 1;
                    }
                }
            }
        }
    }
}
