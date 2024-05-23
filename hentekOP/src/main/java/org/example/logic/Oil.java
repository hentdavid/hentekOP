package org.example.logic;

import java.util.Random;

public class Oil extends Entity {

    public Oil (int x, int y) {
        super(x, y, 110, 60);
    }

    public static Oil createOil(int width, int height) {
        Random random = new Random();
        int x = random.nextInt(551) + 200;
        int y = 615;
        return new Oil(x, y);
    }
}
