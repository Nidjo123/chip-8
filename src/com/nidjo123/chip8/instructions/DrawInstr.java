package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;

public class DrawInstr extends Instruction {
    public DrawInstr(short word) {
        super(word);
    }

    @Override
    public void execute(Chip8.Context context) {
        context.registers[0xF] = 0;
        int width = context.display.getWidth();
        int height = context.display.getHeight();
        int startX = context.registers[registerX] % width;
        int startY = context.registers[registerY] % height;
        for (int y = 0; y < immediate4; y++) {
            if (y >= height) {
                break;
            }
            byte spriteByte = context.memory.getByte(context.indexRegister + y);
            for (int x = 7; x >= 0; x--) {
                int xPos = startX + 7 - x;
                if (xPos >= width) {
                    continue;
                }
                if (((spriteByte >> x) & 0x1) != 0) {
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
