package org.example.logic;

import java.util.Random;

public class Tire extends Entity {

    public Tire (int x, int y) {
        super(x, y, 70, 70);
    }

    public static Tire createTire(int width, int height) {
        Random random = new Random();
        int x = random.nextInt(321) + 320;
        int y = 80;
        return new Tire(x, y);
    }

    public void fall() {
        y += 2;
    }

    public void move() {
        if (getY() >= 700) {
            Random random = new Random();
            int newX = random.nextInt(321) + 320;
            setX(newX);
            setY(-getHeight());
        }
        else {
            setY(getY() + 1);
        }
    }
}
