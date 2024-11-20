package com.nidjo123.chip8.instructions;

public class Instructions {
    public static Instruction decode(short word) {
        if (word == 0x00E0) {
            return new ClearScreenInstr(word);
        } else if (word == 0x00EE) {
            return new RetInstr(word);
        } else {
            switch (Instruction.extractOpCode(word)) {
                case 0x1:
                    return new JumpInstr(word);
                case 0x2:
                    return new CallInstr(word);
                case 0x6:
                    return new SetVXInstr(word);
                case 0x7:
                    return new AddVXInstr(word);
                case 0xA:
                    return new SetIndexInstr(word);
                case 0xC:
                    return new RandomInstr(word);
                case 0xD:
                    return new DrawInstr(word);
            }
        }
        throw new RuntimeException("Unknown instruction code: " + word);
    }
}
