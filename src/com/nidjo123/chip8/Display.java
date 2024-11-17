package com.nidjo123.chip8;


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

    public boolean[] getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
