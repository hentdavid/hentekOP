package org.example.logic;

import org.example.ImageLoader;

import java.awt.*;

public class Box extends Entity {
    public boolean isBroken;
    public int timer;
    public Box (int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(Graphics g) {
        if(isBroken){
            loader = new ImageLoader("Hentek - BOX.png");
        }else{
            loader = new ImageLoader("Hentek - BOX.png");
        }
        g.drawImage(loader.getImage(), x, y, width, height, null);
    }
}
