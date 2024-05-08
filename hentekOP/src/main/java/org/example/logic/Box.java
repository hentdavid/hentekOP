package org.example.logic;

public class Box extends Entity {
    public boolean isBroken;
    public int timer;
    public Box (int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}
