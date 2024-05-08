package org.example.logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity implements KeyListener {
    boolean LEFT;
    boolean RIGHT;
    boolean isCarrying;
    boolean isStunned;
    private int score;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        score = 0;
    }

    public void setStunned(boolean stunned) {
        this.isStunned = stunned;
    }

    public void move() {
        //System.out.println(x);
        if (!isStunned) {
            if (LEFT) {
                x -= 3;
            }
            if (RIGHT) {
                x += 3;
            }
            if (x <= 200) {
                x = 200;
            }
            if (x >= 720) {
                x = 720;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keys = e.getKeyCode();
        if(keys == KeyEvent.VK_A) {
            LEFT = true;
        }
        if(keys == KeyEvent.VK_D) {
            RIGHT = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keys = e.getKeyCode();
        if(keys == KeyEvent.VK_A) {
            LEFT = false;
        }
        if(keys == KeyEvent.VK_D) {
            RIGHT = false;
        }
    }
    public boolean isCarrying() {
        return isCarrying;
    }

    public void setCarrying(boolean carrying) {
        isCarrying = carrying;
    }

    public void Score() {
        score++;
    }
    public int getScore() {
        return score;
    }
}
