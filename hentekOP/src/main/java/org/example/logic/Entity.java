package org.example.logic;

import org.example.ImageLoader;

import java.awt.*;

public class Entity extends Coordinates {
    protected int width;
    protected int height;
    public ImageLoader loader;

    public Entity(int x, int y,int width, int height) {
        super(x,y);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
