package org.example;

import org.example.logic.*;
import org.example.logic.Box;
import javax.swing.*;
import java.awt.*;

public class GameGraphics extends JPanel {
    GameLogic logic;
    ImageLoader gameBackground;
    ImageLoader menuBackground;
    ImageLoader tutorialBackground;
    ImageLoader gameOverBackground;
    ImageLoader gameOverNoTimeBackground;
    ImageLoader buttonPlay;
    JButton playButton;
    ImageLoader buttonTutorial;
    JButton tutorialButton;
    boolean inGame;
    boolean inTutorial;
    boolean gameOver;
    boolean gameOverNoTime;

    GameGraphics(GameLogic logic, GameMenu menu) {
        this.logic = logic;
        this.inGame = false;
        setLayout(null);
        setFocusable(true);
        gameBackground = new ImageLoader("Hentek - BACKGROUND.png");
        menuBackground = new ImageLoader("Hentek - MENU.png");
        tutorialBackground = new ImageLoader("Hentek - TUTORIAL.png");
        gameOverBackground = new ImageLoader("Hentek - GAMEOVER.png");
        gameOverNoTimeBackground = new ImageLoader("Hentek - GAMEOVERNOTIME.png");

        buttonPlay = new ImageLoader("Hentek - PLAYBUTTON.png");
        playButton = menu.getPlayButton();
        playButton.setIcon(new ImageIcon(buttonPlay.getImage()));
        playButton.setBounds(620, 200, buttonPlay.getImage().getWidth(null), buttonPlay.getImage().getHeight(null));
        playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.addActionListener(e -> startGame());
        add(playButton);

        buttonTutorial = new ImageLoader("Hentek - TUTORIALBUTTON.png");
        tutorialButton = menu.getTutorialButton();
        tutorialButton.setIcon(new ImageIcon(buttonTutorial.getImage()));
        tutorialButton.setBounds(620, 350, buttonTutorial.getImage().getWidth(null), buttonTutorial.getImage().getHeight(null));
        tutorialButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tutorialButton.setContentAreaFilled(false);
        tutorialButton.setBorderPainted(false);
        tutorialButton.addActionListener(e -> startTutorial());
        add(tutorialButton);
    }

    public void startGame() {
        inGame = true;
        inTutorial = false;
        gameOver = false;
        gameOverNoTime = false;
        playButton.setVisible(false);
        tutorialButton.setVisible(false);
        setFocusable(true);
        requestFocusInWindow();
    }

    public void startTutorial() {
        inTutorial = true;
        inGame = false;
        gameOver = false;
        gameOverNoTime = false;
        tutorialButton.setVisible(false);
        repaint();

    }

    public void endGame() {
        gameOver = true;
        inGame = false;
        inTutorial = false;
        gameOverNoTime = false;
        repaint();
    }

    public void endGameNoTime() {
        gameOverNoTime = true;
        inGame = false;
        inTutorial = false;
        gameOver = false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(gameBackground.getImage(), 0, 0, logic.getWidth(), logic.getHeight(), null);

            Player player = logic.getPlayer();
            drawPlayer(g, player);

            for (Box box : logic.getBoxes()) {
                drawBox(g, box);
            }

            for (Drink drink : logic.getDrinks()) {
                drawDrink(g, drink);
            }

            for (Tire tire : logic.getTires()) {
                drawTire(g, tire);
            }

            Oil oil = logic.getOil();
            if (oil != null) {
                drawOil(g, oil);
            }

            g.setColor(Color.BLACK);
            g.drawString("score: " + logic.getPlayer().getScore(), logic.getWidth() - 100, 280);
            g.drawString("time remaining: " + logic.getTimeRemaining(), logic.getWidth() - 250, 280);
        }
        else if (inTutorial) {
            g.drawImage(tutorialBackground.getImage(), 0, 0, logic.getWidth(), logic.getHeight(), null);
        }
        else if (gameOver) {
            g.drawImage(gameOverBackground.getImage(), 0, 0, logic.getWidth(), logic.getHeight(), null);
        }
        else if (gameOverNoTime) {
            g.drawImage(gameOverNoTimeBackground.getImage(), 0, 0, logic.getWidth(), logic.getHeight(), null);
        }
        else {
            g.drawImage(menuBackground.getImage(), 0, 0, logic.getWidth(), logic.getHeight(), null);
        }
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

    private void drawOil(Graphics g, Oil oil) {
        oil.loader = new ImageLoader("Hentek - OIL.png");
        g.drawImage(oil.loader.getImage(), oil.getX(), oil.getY(), oil.getWidth(), oil.getHeight(), null);
    }
}
