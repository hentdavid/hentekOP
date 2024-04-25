package org.example;

import org.example.logic.Box;

import java.awt.*;

public class GameGraphics {
    GameLogic logic;
    ImageLoader background, conveyor;
    GameGraphics(GameLogic logic) {
        background = new ImageLoader("Hentek - BACKGROUND.png");
        conveyor = new ImageLoader("Hentek - CONVEYOR.png");
        this.logic = logic;
    }
    public void draw(Graphics g) {
        g.drawImage(background.image, 0, 0, logic.winWidth, logic.winHeight, null);
        for(Box box: logic.getBoxes()){
            box.draw(g);
        }
        g.drawImage(conveyor.getImage(), 0,0,logic.winWidth, logic.winHeight, null);
        logic.getPlayer().draw(g);
    }
}
