package org.example;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    JFrame frame;
    GameLogic logic;
    GameGraphics graphics;
    GameMenu menu;
    int width = 1024;
    int height = 768;

    public GamePanel() {
        frame = new JFrame("CAR PARTS WAREHOUSE");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null);
        logic = new GameLogic(width, height, this);
        menu = new GameMenu(this);
        graphics = new GameGraphics(logic, menu);
        frame.add(graphics);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        setFocusable(true);
        requestFocusInWindow();
        graphics.addKeyListener(logic);
    }

    public void gamePlay() {
        graphics.startGame();
        Thread thread = new Thread(this);
        thread.start();
        graphics.setFocusable(true);
        graphics.requestFocusInWindow();
        graphics.addKeyListener(logic.getPlayer());
    }

    public void gameTutorial() {
        graphics.startTutorial();
    }

    public void gameEnd() {
        graphics.endGame();
    }

    public void gameEndNoTime() {
        graphics.endGameNoTime();
    }

    @Override
    public void run() {
        while (graphics.inGame) {
            logic.update();
            graphics.repaint();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
