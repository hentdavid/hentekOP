package org.example;

import javax.swing.*;

public class GameMenu extends JPanel {
    private JButton playButton;
    private JButton tutorialButton;

    public GameMenu(GamePanel gamePanel) {
        playButton = new JButton();
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(true);
        playButton.addActionListener(e -> gamePanel.gamePlay());
        add(playButton);
        tutorialButton = new JButton();
        tutorialButton.setOpaque(false);
        tutorialButton.setContentAreaFilled(false);
        tutorialButton.setBorderPainted(false);
        tutorialButton.addActionListener(e -> gamePanel.gameTutorial());
        add(tutorialButton);
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getTutorialButton() {
        return tutorialButton;
    }
}
