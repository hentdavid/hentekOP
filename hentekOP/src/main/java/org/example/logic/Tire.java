package org.example.logic;

import java.util.Random;

public class Tire extends Entity {

    public Tire (int x, int y) {
        super(x, y, 50, 50);
    }

    public static Tire createTire(int width, int height) {
        Random random = new Random();
        int x = random.nextInt(251) + 250;
        int y = 80;
        return new Tire(x, y);
    }

    public void fall() {
        y += 2;
    }

    public void move() {
        if (getY() >= 680) {
            Random random = new Random();
            int newX = random.nextInt(251) + 250;
            setX(newX);
            setY(-getHeight());
        }
        else {
            setY(getY() + 1);
        }
    }
}
