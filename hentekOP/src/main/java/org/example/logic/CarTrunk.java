package org.example.logic;

import java.awt.*;

public class CarTrunk extends Coordinates{
    private Color color;
    private boolean active;

    public CarTrunk(int x,int y) {
        super(x,y);
        this.active = true;
    }
    public void draw(Graphics g){

    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
