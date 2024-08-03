package org.example;

import org.example.battle.ProcessBattle;

import javax.swing.*;

/**
 * Hello game!
 *
 */
public class Game extends JFrame {
    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        add(new ProcessBattle());
        setTitle("First game try");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
