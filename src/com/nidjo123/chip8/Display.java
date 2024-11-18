package com.nidjo123.chip8;


import java.util.Arrays;

public class Display {
    private int width, height;
    private boolean[] pixels;

    public Display(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new boolean[width * height];
    }

    public void setPixel(int x, int y, boolean value) {
        pixels[x + y * width] = value;
    }

    public boolean getPixel(int x, int y) {
        return pixels[x + y * width];
    }

    public void togglePixel(int x, int y) {
        pixels[x + y * width] = !pixels[x + y * width];
    }

    public boolean[] getPixels() {
        return pixels;
    }

    public void clear() {
        Arrays.fill(pixels, false);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
