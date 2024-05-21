package org.example.logic;

import java.util.Random;

public class Drink extends Entity {

    public Drink (int x, int y) {
        super(x, y, 20, 36);
    }

    public static Drink createDrink () {
        Random random = new Random();
        int[][] position = {{250, 170}, {545, 170}, {490, 400}};
        int[] randomPosition = position[random.nextInt(3)];
        return new Drink(randomPosition[0], randomPosition[1]);
    }

    public void fall() {
        y += 2;
    }

    public void move() {
        if (getY() >= 720) {
            Random random = new Random();
            int randomPosition = random.nextInt(3);
            setX(randomPosition);
        }
    }
}
