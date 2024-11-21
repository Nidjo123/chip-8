package com.nidjo123.chip8.instructions;

import com.nidjo123.chip8.Chip8;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionTest {
    private static class MyInstruction extends Instruction {
        public MyInstruction(short word) {
            super(word);
        }

        @Override
        public void execute(Chip8.Context context) {

        }
    }

    @Test
    public void testInstructionData() {
        MyInstruction instruction = new MyInstruction((short) 0x1234);
        assertEquals(0x1, instruction.opCode);
        assertEquals(0x2, instruction.registerX);
        assertEquals(0x3, instruction.registerY);
        assertEquals(0x4, instruction.immediate4);
        assertEquals(0x34, instruction.immediate8);
        assertEquals(0x234, instruction.immediate12);
    }
}