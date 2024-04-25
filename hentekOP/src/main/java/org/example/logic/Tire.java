package org.example.logic;

public class Tire extends Entity {
    private int damage;

    public Tire (int x, int y, String url, int damage) {
        super(x, y, 10, 10);
        this.damage = damage;
    }

    public void move() {

    }
}
