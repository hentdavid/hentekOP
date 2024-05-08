package org.example;

import org.example.logic.Box;
import org.example.logic.Player;
import org.example.logic.Tire;

import java.awt.*;

public class GameGraphics {
    GameLogic logic;
    ImageLoader background;
    ImageLoader conveyor;

    GameGraphics(GameLogic logic) {
        background = new ImageLoader("Hentek - BACKGROUND.png");
        conveyor = new ImageLoader("Hentek - CONVEYOR.png");
        this.logic = logic;
    }

    public void draw(Graphics g) {
        g.drawImage(background.image, 0, 0, logic.width, logic.height, null);
        g.drawImage(conveyor.getImage(), 0,0,logic.width, logic.height, null);

        Player player = logic.getPlayer();
            if (player.isCarrying()) {
                player.loader = new ImageLoader("Hentek - WAREHOUSEMANWITHBOX.png");
            }
            else {
                player.loader = new ImageLoader("Hentek - WAREHOUSEMAN.png");
            }
            g.drawImage(player.loader.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);

        for (Box box: logic.getBoxes()) {
            if (box.isBroken) {
                box.loader = new ImageLoader("Hentek - BROKENBOX.png");
                box.setWidth(100);
                box.setHeight(68);
            }
            else {
                box.loader = new ImageLoader("Hentek - BOX.png");
            }
            g.drawImage(box.loader.getImage(), box.getX(), box.getY(), box.getWidth(), box.getHeight(), null);
        }

        for (Tire tire: logic.getTires()) {
            tire.loader = new ImageLoader("Hentek - TIRE.png");
            g.drawImage(tire.loader.getImage(), tire.getX(), tire.getY(),tire.getWidth(), tire.getHeight(), null);
        }

        g.drawImage(conveyor.getImage(), 0,0,logic.width, logic.height, null);
    }
}
