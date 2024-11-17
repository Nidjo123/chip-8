package com.nidjo123.chip8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Rom {
    private byte[] content;

    public static Rom from_file(Path path) throws IOException {
        Rom rom = new Rom();
        rom.content = Files.readAllBytes(path);
        return rom;
    }

    public int getSize() {
        return content.length;
    }

    public byte[] getContent() {
        return Arrays.copyOf(content, content.length);
    }
}
