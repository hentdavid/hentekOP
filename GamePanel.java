package org.example;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    GameLogic logic;
    GameGraphics graphics;
    int width = 1024;
    int height = 768;

    public GamePanel() {
        JFrame frame = new JFrame("CAR PARTS WAREHOUSE");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        logic = new GameLogic(width, height);
        graphics = new GameGraphics(logic);
        frame.add(graphics);
        Thread thread = new Thread(this);
        thread.start();
        setFocusable(true);
        requestFocus();
        addKeyListener(logic.getPlayer());
    }

    @Override
    public void run() {
        while (true) {
            logic.update();
            graphics.repaint();
            try {
                Thread.sleep(1000/60);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
