package org.example;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    GameLogic logic;
    GameGraphics graphics;
    int winWidth = 1024;
    int winHeight = 768;

    GamePanel() {
        setPreferredSize(new Dimension(winWidth, winHeight));
        logic = new GameLogic(winWidth, winHeight);
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

    }

    @Override
    public void run() {
        while(true){
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
