package org.example;

public class Game {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GamePanel();
            }
        });
    }
}
