package org.example;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GamePanel panel;

    public GameFrame() throws HeadlessException {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Game");
        panel = new GamePanel();
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
