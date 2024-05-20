package org.example;

import org.example.logic.Box;
import org.example.logic.Drink;
import org.example.logic.Player;
import org.example.logic.Tire;

import javax.swing.*;
import java.awt.*;

public class GameGraphics extends JPanel {
    GameLogic logic;
    ImageLoader background;
    ImageLoader menu;

    GameGraphics(GameLogic logic) {
        this.logic = logic;
        background = new ImageLoader("Hentek - BACKGROUND.png");
        //menu = new ImageLoader("Hentek - MENU.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, logic.getWidth(), logic.getHeight(), null);

        Player player = logic.getPlayer();
        drawPlayer(g, player);

        for (Box box : logic.getBoxes()) {
            drawBox(g, box);
        }

        for (Tire tire : logic.getTires()) {
            drawTire(g, tire);
        }

        for (Drink drink : logic.getDrinks()) {
            drawDrink(g, drink);
        }

        g.setColor(Color.BLACK);
        g.drawString("score: " + logic.getPlayer().getScore(), logic.getWidth() - 100, 280);
    }

    private void drawPlayer(Graphics g, Player player) {
        player.setY(320);
        player.setWidth(100);
        player.setHeight(300);
        if (player.isCarrying() && player.isStunned()) {
            player.loader = new ImageLoader("Hentek - WAREHOUSEMANBOXHIT.png");
            player.setY(300);
            player.setWidth(110);
            player.setHeight(320);
        } else if (player.isCarrying()) {
            player.loader = new ImageLoader("Hentek - WAREHOUSEMANWITHBOX.png");
            player.setWidth(110);
        } else if (player.isStunned()) {
            player.loader = new ImageLoader("Hentek - WAREHOUSEMANHIT.png");
            player.setY(300);
            player.setHeight(320);
        } else {
            player.loader = new ImageLoader("Hentek - WAREHOUSEMAN.png");
        }
        g.drawImage(player.loader.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);
    }

    private void drawBox(Graphics g, Box box) {
            if (box.isBroken) {
                box.loader = new ImageLoader("Hentek - BROKENBOX.png");
                box.setWidth(85);
                box.setHeight(53);
            } else {
                box.loader = new ImageLoader("Hentek - BOX.png");
                box.setWidth(45);
                box.setHeight(45);
            }
            g.drawImage(box.loader.getImage(), box.getX(), box.getY(), box.getWidth(), box.getHeight(), null);
        }

    private void drawTire(Graphics g, Tire tire) {
            tire.loader = new ImageLoader("Hentek - TIRE.png");
            g.drawImage(tire.loader.getImage(), tire.getX(), tire.getY(), tire.getWidth(), tire.getHeight(), null);
    }

    private void drawDrink(Graphics g, Drink drink) {
        drink.loader = new ImageLoader("Hentek - SMALLERDRINK.png");
        g.drawImage(drink.loader.getImage(), drink.getX(), drink.getY(), drink.getWidth(), drink.getHeight(), null);
    }
}
