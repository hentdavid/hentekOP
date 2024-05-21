package org.example;

import org.example.logic.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameLogic {
    Player player;
    public final int width;
    public final int height;
    private ArrayList<Box> boxes;
    private ArrayList<Box> boxesToRemove;
    private ArrayList<Tire> tires;
    private final ArrayList<Drink> drinks;
    boolean canCarry;
    int boxSpawnTimer = 100;
    int boxSpawnInterval = 180;
    private boolean tireAdded = false;

    public GameLogic(int width, int height) {
        this.width = width;
        this.height = height;
        boxesToRemove = new ArrayList<>();
        this.boxes = new ArrayList<>();
        this.player = new Player(200, 340, width, height);
        this.tires = new ArrayList<>();
        this.drinks = new ArrayList<>();
        tireSpawner();
        drinkSpawner();
    }

    public void update() {
        player.move();
        tireCollision(new Timer());
        drinkCollision(new Timer());
        updatePlayer();
        updateBoxes();
        updateTires();
        updateDrinks();
    }

    public void updatePlayer() {
        if (player.getX() > 300) {
            canCarry = false;
        }
        else {
            canCarry = true;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void updateBoxes() {
        boxSpawnTimer++;
        if (boxSpawnTimer >= boxSpawnInterval) {
            boxes.add(new Box(-100,455, 50,50));
            boxSpawnTimer = 0;
        }
        for (Box box : boxes) {
            if (!canCarry ) {
                if (box.getX() > 150) {
                    box.isBroken = true;
                }
            }
            else if (box.getX() > 150 && !getPlayer().isCarrying() && !box.isBroken) {
                player.setCarrying(true);
                boxesToRemove.add(box);

            }
            else if (box.getX() > 150 && getPlayer().isCarrying()) {
                box.isBroken = true;
            }
            if (box.isBroken) {
                box.setX(200);
                box.setY(600);
                box.timer++;
                if (box.timer >= 150) {
                    boxesToRemove.add(box);
                }
            }
            box.setX(box.getX() + 2);
        }
        if (player.getX() == 710 && player.isCarrying()) {
            player.Score();
            player.setCarrying(false);
        }
        if (player.getScore() > 3 && !tireAdded) {
            tires.add(new Tire(300, 100));
            tireAdded = true;
        }
        boxes.removeAll(boxesToRemove);
    }
    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void updateTires() {
        for (Tire tire : tires) {
            tire.fall();
            tire.move();
        }
    }

    public void tireCollision(Timer timer) {
        Rectangle playerRectangle = player.getRect();
        for (Tire tire : tires) {
            Rectangle tireRectangle = tire.getRect();
            if (playerRectangle.intersects(tireRectangle)) {
                player.setStunned(true);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        player.setStunned(false);
                    }
                }, 2000);
            }
        }
    }

    public void tireSpawner() {
        tires.add(Tire.createTire(width, height));
    }

    public ArrayList<Tire> getTires() {
        return tires;
    }

    public void updateDrinks() {
        ArrayList<Drink> drinksToRemove = new ArrayList<>();
        for (Drink drink : drinks) {
            drink.fall();
            drink.move();
        }
        drinks.removeAll(drinksToRemove);
    }

    public void drinkCollision(Timer timer) {
        Rectangle playerRectangle = player.getRect();
        for (Drink drink : drinks) {
            Rectangle drinkRectangle = drink.getRect();
            if (playerRectangle.intersects(drinkRectangle)) {
                drinks.remove(drink);
                player.setSpeedMultiplier(1.5);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        player.setSpeedMultiplier(1.0);
                    }
                }, 3000);
                break;
            }
        }
    }

    public void drinkSpawner() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                drinks.add(Drink.createDrink());
            }
        }, 0, 7000);
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
