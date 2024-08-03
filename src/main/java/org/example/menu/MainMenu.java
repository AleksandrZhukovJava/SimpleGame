package org.example.menu;

import org.example.Game;
import org.example.util.BackgroundPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JPanel {
    public MainMenu(Game game) {
        setLayout(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel("/static/background/mainmenu.jpg");
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 800, 600);


        Border buttonBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 10, 10), 2),
                BorderFactory.createLineBorder(Color.BLACK, 2)
        );

        // Define a custom font for the buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 18); // Slightly larger font size

        // Create and customize buttons
        JButton battleButton = createCustomButton("Battle", buttonFont, buttonBorder);
        battleButton.setBounds(50, 50, 200, 40); // Larger button size
        battleButton.addActionListener(e -> game.startBattle());
        backgroundPanel.add(battleButton);

        JButton mapButton = createCustomButton("Map", buttonFont, buttonBorder);
        mapButton.setBounds(50, 100, 200, 40); // Larger button size
        backgroundPanel.add(mapButton);

        JButton saveButton = createCustomButton("Save", buttonFont, buttonBorder);
        saveButton.setBounds(50, 150, 200, 40); // Larger button size
        backgroundPanel.add(saveButton);

        JButton loadButton = createCustomButton("Load", buttonFont, buttonBorder);
        loadButton.setBounds(50, 200, 200, 40); // Larger button size
        backgroundPanel.add(loadButton);

        JButton exitButton = createCustomButton("Exit", buttonFont, buttonBorder);
        exitButton.setBounds(50, 250, 200, 40); // Larger button size
        exitButton.addActionListener(e -> System.exit(0));
        backgroundPanel.add(exitButton);

        add(backgroundPanel);
    }

    private JButton createCustomButton(String text, Font font, Border border) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBorder(border);
        button.setBackground(Color.BLACK);
        button.setForeground(new Color(93, 119, 119));
        button.setFocusPainted(false); // Remove focus highlight
        button.setOpaque(true); // Ensure background color is painted

        // Add mouse listener for hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(30, 2, 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.BLACK);
            }
        });

        return button;
    }
}
