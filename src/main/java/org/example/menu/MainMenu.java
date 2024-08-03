package org.example.menu;

import org.example.Game;
import org.example.util.BackgroundPanel;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    public MainMenu(Game game) {
        setLayout(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel("/static/background/mainmenu.jpg");
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 800, 600);

        JButton battleButton = new JButton("Battle");
        battleButton.setBounds(50, 50, 150, 30);
        battleButton.addActionListener(e -> game.startBattle());
        backgroundPanel.add(battleButton);

        JButton mapButton = new JButton("Map");
        mapButton.setBounds(50, 100, 150, 30);
        backgroundPanel.add(mapButton);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(50, 150, 150, 30);
        backgroundPanel.add(saveButton);

        JButton loadButton = new JButton("Load");
        loadButton.setBounds(50, 200, 150, 30);
        backgroundPanel.add(loadButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(50, 250, 150, 30);
        exitButton.addActionListener(e -> System.exit(0));
        backgroundPanel.add(exitButton);

        add(backgroundPanel);
    }
}
