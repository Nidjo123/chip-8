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
                case 0x3:
                    return new SkipVXImmEqInstr(word);
                case 0x4:
                    return new SkipVXImmNotEqInstr(word);
                case 0x5:
                    return new SkipVXVYEq(word);
                case 0x6:
                    return new SetVXInstr(word);
                case 0x7:
                    return new AddVXInstr(word);
                case 0x8:
                    int lastNibble = word & 0xF;
                    switch (lastNibble) {
                        case 0x0:
                            return new SetVXVYInstr(word);
                        case 0x1:
                            return new BinaryOrInstr(word);
                        case 0x2:
                            return new BinaryAndInstr(word);
                        case 0x3:
                            return new BinaryXorInstr(word);
                        case 0x4:
                            return new AddVXVYInstr(word);
                        case 0x5:
                            return new SubVXVYInstr(word);
                        case 0x6:
                            return new ShrVXInstr(word);
                        case 0x7:
                            return new SubVYVXInstr(word);
                        case 0xE:
                            return new ShlVXInstr(word);
                    }
                    throw new InvalidInstruction("Invalid last nibble: " + Integer.toHexString(lastNibble));
                case 0x9:
                    return new SkipVXVYNotEq(word);
                case 0xA:
                    return new SetIndexInstr(word);
                case 0xB:
                    return new JumpOffsetInstr(word);
                case 0xC:
                    return new RandomInstr(word);
                case 0xD:
                    return new DrawInstr(word);
            }
        }
        throw new InvalidInstruction("Unknown instruction code: " + Integer.toHexString(Short.toUnsignedInt(word)));
    }
}
