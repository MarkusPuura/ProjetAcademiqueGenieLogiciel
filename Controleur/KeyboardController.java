package Controleur;

import javax.swing.*;

import Vue.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyboardController extends JFrame {
    private final GamePanel gamePanel;

    public KeyboardController(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void initializeKeyboardListener() {
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();

                if (keyChar == 'p' || keyChar == 'P') {
                    gamePanel.miseEnPauseDuJeu();
                } else if (keyChar == 'q' || keyChar == 'Q') {
                    System.exit(0);
                } else if (keyChar == 'a' || keyChar == 'A') {
                    gamePanel.increaseGameSpeed();
                } else if (keyChar == 'z' || keyChar == 'Z') {
                    gamePanel.decreaseGameSpeed();
                } else if (keyChar == 'm') {
                    gamePanel.musique.stopperMusique();

                } else if (keyChar == 'M') {
                    gamePanel.musique.jouerMusiqueEnBoucle("../sons/musique.wav");
                }
            }
        });
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

    }
}
