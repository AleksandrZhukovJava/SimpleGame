package org.example;

import org.example.battle.ProcessBattle;
import org.example.menu.MainMenu;


import javax.swing.*;

/**
 * Hello game!
 */
public class Game extends JFrame {
    private JPanel currentPanel;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        setTitle("First game try");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        showMainMenu();
    }

    public void showMainMenu() {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = new MainMenu(this);
        add(currentPanel);
        revalidate();
        repaint();
    }

    public void startBattle() {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = new ProcessBattle();
        add(currentPanel);
        revalidate();
        repaint();
    }
}
