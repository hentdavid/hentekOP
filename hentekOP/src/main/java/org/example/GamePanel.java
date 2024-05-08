package org.example;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    GameLogic logic;
    GameGraphics graphics;
    int width = 1024;
    int height = 768;

    GamePanel() {
        setPreferredSize(new Dimension(width, height));
        logic = new GameLogic(width, height);
        graphics = new GameGraphics(logic);
        Thread thread = new Thread(this);
        thread.start();
        setFocusable(true);
        requestFocus();
        addKeyListener(logic.player);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics.draw(g);
        g.setColor(Color.BLACK);
        g.drawString("score: " + logic.getPlayer().getScore(), getWidth() - 100, 30);
    }

    @Override
    public void run() {
        while(true) {
            logic.update();
            repaint();
            try {
                Thread.sleep(1000/60);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
