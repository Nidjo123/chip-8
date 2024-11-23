import com.nidjo123.chip8.Chip8;
import com.nidjo123.chip8.Rom;
import com.nidjo123.chip8.Ticker;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main implements Runnable {
    private static final int WIDTH = 64;
    private static final int HEIGHT = 32;
    private static final int SCALE = 10;
    private Canvas canvas = new Canvas();
    private BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels;
    private Chip8 chip8 = new Chip8(WIDTH, HEIGHT);
    private Path romPath;

    public Main(Path romPath) {
        this.romPath = romPath;
        pixels = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
    }

    public static void main(String[] args) {
        Path romPath = null;
        if (args.length == 0) {
            romPath = getSelectedROM();
            if (romPath == null) {
                System.exit(1);
            }
        } else {
            try {
                romPath = Paths.get(args[0]);
            } catch (InvalidPathException e) {
                System.err.println("Invalid path: " + args[0] + "(" + e.getMessage() + ")");
                System.exit(1);
            }
        }
        startChip8(romPath);
    }

    private static Path getSelectedROM() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnState = fileChooser.showDialog(null, "Load CHIP-8 ROM");
        if (returnState == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().toPath();
        }
        return null;
    }

    private static void startChip8(Path romPath) {
        JFrame frame = new JFrame("CHIP-8");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Main main = new Main(romPath);
        Dimension dimension = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        main.canvas.setMinimumSize(dimension);
        main.canvas.setPreferredSize(dimension);
        frame.add(main.canvas);

        frame.pack();
        frame.setVisible(true);

        Thread chip8Thread = new Thread(main);
        chip8Thread.start();
        try {
            chip8Thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void render() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        if (bufferStrategy == null) {
            canvas.createBufferStrategy(2);
            return;
        }

        boolean[] displayPixels = this.chip8.getDisplayPixels();
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                pixels[y * WIDTH + x] = displayPixels[y * WIDTH + x] ? 0xFFFFFF : 0x000000;
            }
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(bufferedImage, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        graphics.dispose();
        bufferStrategy.show();
    }

    @Override
    public void run() {
        try {
            Rom rom = Rom.from_file(this.romPath);
            this.chip8.loadRom(rom);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Ticker timerTicker = new Ticker(60);
        timerTicker.addCallback(chip8::tickTimers);

        Ticker logicTicker = new Ticker(4);
        logicTicker.addCallback(chip8::tick);

        while (true) {
            timerTicker.update();
            logicTicker.update();

            render();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}