package com.nidjo123.chip8;

import com.nidjo123.chip8.instructions.Instruction;
import com.nidjo123.chip8.instructions.Instructions;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Chip8 {
    public static class Context {
        public Memory memory = new Memory();
        public int programCounter = 0x200;
        public int indexRegister;
        public Stack<Integer> addressStack = new Stack<>();
        public byte[] registers = new byte[16];
        public Timer delayTimer = new Timer();
        public Timer soundTimer = new Timer();
        public Display display;

        public Context(int width, int height) {
            display = new Display(width, height);
        }
    }

    private final Context context;

    public Chip8(int width, int height) {
        context = new Context(width, height);
    }

    public void loadRom(Rom rom) {
        loadRom(rom, 0x200);
    }

    public void loadRom(Rom rom, int startAddress) {
        if (context.memory.getSizeBytes() - startAddress < rom.getSize()) {
            throw new RuntimeException("Rom doesn't fit into memory");
        }
        byte[] romBytes = rom.getContent();
        context.memory.setBytes(romBytes, startAddress);
    }

    public void tickTimers() {
        context.delayTimer.tick();
        context.soundTimer.tick();
    }

    private short fetchInstruction() {
        var memory = context.memory;
        int instruction = Byte.toUnsignedInt(memory.getByte(context.programCounter)) << 8;
        instruction |= Byte.toUnsignedInt(memory.getByte(context.programCounter + 1));
        context.programCounter += 2;
        return (short) (instruction & 0xFFFF);
    }

    public void tick() {
        short word = fetchInstruction();
        Instruction instruction = Instructions.decode(word);
        instruction.execute(context);
    }

    public boolean[] getDisplayPixels() {
        boolean[] pixels = context.display.getPixels();
        return Arrays.copyOf(pixels, pixels.length);
    }
}
