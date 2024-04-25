package org.example;

import org.example.logic.*;

import java.awt.*;
import java.util.ArrayList;

public class GameLogic {
    Player player;
    public int winWidth;
    public int winHeight;
    private ArrayList<Box> boxes;
    private ArrayList<Box> boxesToRemove;
    private ArrayList<Tire> tires;
    private ArrayList<Drink> drinks;
    int score;
    private CarTrunk carTrunk;
    boolean canCarry;
    int boxSpawnTimer = 100;
    int boxSpawnInterval = 100;

    public Player getPlayer() {
        return player;
    }

    public GameLogic(int winWidth, int winHeight) {
        this.winHeight = winHeight;
        this.winWidth = winWidth;
        boxesToRemove = new ArrayList<>();
        this.boxes = new ArrayList<>();
        this.player = new Player(80, 340, 80, 290);
        this.tires = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.carTrunk = new CarTrunk(10, 10);
    }

    public void update() {
        player.move();
        collision();
        updatePlayer();
        updateBoxes();
    }
    public void collision(){

    }
    public void updatePlayer(){
        if(player.getX() > 300){
            canCarry = false;
        }else{
            canCarry = true;
        }
    }

    public void updateBoxes(){
        boxSpawnTimer++;
        if(boxSpawnTimer >= boxSpawnInterval){
            boxes.add(new Box(-100,450, 50,50));
            boxSpawnTimer = 0;
        }
        for(Box box : boxes){
            if(!canCarry ){
                if(box.getX() > 150){
                    box.isBroken = true;
                }
            }else if(box.getX() > 150 && !getPlayer().isCarrying() && !box.isBroken){
                player.setCarrying(true);
                boxesToRemove.add(box);

            }else if(box.getX() > 150 && getPlayer().isCarrying()){
                box.isBroken = true;
            }
            if(box.isBroken){
                box.setX(200);
                box.setY(600);
                box.timer++;
                if(box.timer >= 150){
                    boxesToRemove.add(box);
                }
            }
        box.setX(box.getX() + 2);
        }
        if(player.getX() == 720){
            player.setCarrying(false);
            score++;
        }
        boxes.removeAll(boxesToRemove);
    }
    public ArrayList<Box> getBoxes() {
        return boxes;
    }
}

