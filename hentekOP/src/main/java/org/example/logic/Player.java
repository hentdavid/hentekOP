package org.example.logic;

import org.example.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity implements KeyListener {
    boolean LEFT;
    boolean RIGHT;
    boolean isCarrying;
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public void draw(Graphics g){
        if(isCarrying){
            loader = new ImageLoader("Hentek - WAREHOUSEMANWITHBOX.png");
        }else{
            loader = new ImageLoader("Hentek - WAREHOUSEMAN.png");
        }
        g.drawImage(loader.getImage(), x, y, width, height, null);
    }

    public void move() {
        System.out.println(x);
        if (LEFT) {
            x -= 3;
        }
        if (RIGHT) {
            x += 3;
        }
        if(x <= 200){
            x = 200;
        }
        if(x >= 720){
            x = 720;
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
}
