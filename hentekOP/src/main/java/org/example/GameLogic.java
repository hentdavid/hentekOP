package org.example;

import org.example.logic.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class GameLogic implements KeyListener {
    Player player;
    public final int width;
    public final int height;
    private ArrayList<Box> boxes;
    private ArrayList<Box> boxesToRemove;
    private ArrayList<Tire> tires;
    private ArrayList<Drink> drinks;
    private Oil oil;
    boolean canCarry;
    int boxSpawnTimer = 100;
    int boxSpawnInterval = 180;
    private boolean tireAdded = false;
    private boolean oilAdded = false;
    private int oilUpdateCounter = 0;
    private Timer oilSpawnTimer;
    private GamePanel gamePanel;
    private Timer gameTimer;
    private int timeRemaining;

    public GameLogic(int width, int height, GamePanel gamePanel) {
        this.width = width;
        this.height = height;
        this.gamePanel = gamePanel;
        boxesToRemove = new ArrayList<>();
        this.boxes = new ArrayList<>();
        this.player = new Player(200, 340, width, height);
        this.tires = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.timeRemaining = 200;
        tireSpawner();
        drinkSpawner();
        startGameTimer();
    }

    private void startGameTimer() {
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeRemaining--;
                if (timeRemaining <= 0) {
                    gamePanel.gameEndNoTime();
                    gameTimer.cancel();
                }
            }
        }, 1000, 1000);
    }

    public void update() {
        player.move();
        tireCollision(new Timer());
        drinkCollision(new Timer());
        oilCollision(new Timer());
        updatePlayer();
        updateBoxes();
        updateTires();
        updateDrinks();
        updateOil();
    }

    public void updatePlayer() {
        if (player.getX() > 300) {
            canCarry = false;
        }
        else {
            canCarry = true;
        }

        if (player.getScore() == 3 && !tireAdded) {
            tires.add(new Tire(300, 100));
            tireAdded = true;
        }
        if (player.getScore() == 6 && !oilAdded) {
            oilSpawner();
            oilAdded = true;
        }
        if (player.getScore() == 10) {
            gamePanel.gameEnd();
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
        if (player.getX() == 720 && player.isCarrying()) {
            player.Score();
            player.setCarrying(false);
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

    public void updateOil() {
        oilUpdateCounter++;
        if (oilUpdateCounter >= 600) {
            oilUpdateCounter = 0;
        }
    }

    public void oilCollision(Timer timer) {
        if (oil == null) {
            return;
        }
        Rectangle playerRectangle = player.getRect();
        Rectangle oilRectangle = oil.getRect();
        if (playerRectangle.intersects(oilRectangle)) {
            player.setSpeedMultiplier(0.5);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    player.setSpeedMultiplier(1.0);
                }
            }, 2500);
        }
    }

    public void oilSpawner() {
        if (oilSpawnTimer != null) {
            oilSpawnTimer.cancel();
        }
        oilSpawnTimer = new Timer();
        oilSpawnTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run () {
                oil = Oil.createOil(width, height);
            }
        }, 0, 5000);
    }

    public Oil getOil() {
        return oil;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
