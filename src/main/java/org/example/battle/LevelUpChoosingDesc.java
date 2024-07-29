package org.example.battle;

import org.example.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class LevelUpChoosingDesc {
    private final Set<JButton> buttons = new HashSet<>();
    private Player player;

    public void execute(ProcessBattle processGame) {
        player = processGame.getPlayer();
        JDialog dialog = new JDialog((Frame) null, "Level Up!", true);
        dialog.setLayout(new BorderLayout());

        // Load the background image
        ImageIcon backgroundImageIcon = new ImageIcon("path/to/your/background/image.png");
        JLabel backgroundLabel = new JLabel(backgroundImageIcon); //todo не работает
        backgroundLabel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("<html>Level Up!<br>Choose upgrade option<br></html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Cooper Black", Font.BOLD, 20));
        titleLabel.setForeground(new Color(50, 50, 50)); // Text color
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setOpaque(false);

        fillRandomImprovementButtons(dialog, buttonPanel);

        backgroundLabel.add(titleLabel, BorderLayout.NORTH);
        backgroundLabel.add(buttonPanel, BorderLayout.CENTER);

        dialog.add(backgroundLabel);

        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(processGame);

        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Ignore close attempts
            }
        });

        dialog.setVisible(true);
        buttons.clear();
    }

    private void fillRandomImprovementButtons(JDialog dialog, JPanel panel) {
        buttons.add(getNewLevelUpOptionButton(
                "Health",
                Player::lvlUpAndIncreaseHealth,
                dialog));
        buttons.add(getNewLevelUpOptionButton(
                "Attack",
                Player::lvlUpAndIncreaseDamage,
                dialog));
        buttons.add(getNewLevelUpOptionButton(
                "Critical chance",
                Player::lvlUpAndIncreaseCriticalChance,
                dialog));
        buttons.add(getNewLevelUpOptionButton(
                "Missing chance",
                Player::lvlUpAndIncreaseMissingChance,
                dialog));

        buttons.stream()
                .limit(player.getLevelUpOptionsAmount())
                .sorted(Comparator.comparing(JButton::getText))
                .forEach(panel::add);
    }

    private JButton getNewLevelUpOptionButton(String message, Consumer<Player> movement,
                                              JDialog dialog) {
        JButton option = new JButton(message);

        option.setFont(new Font("Cooper Black", Font.PLAIN, 28));
        option.setPreferredSize(new Dimension(200, 100));

        option.addActionListener(e -> {
            movement.accept(player);
            dialog.dispose();
        });

        option.setBackground(new Color(140, 140, 140)); // Цвет фона
        option.setForeground(new Color(80, 20, 20)); // Цвет текста
        option.setFocusPainted(false);
        option.setBorderPainted(false);

        return option;
    }
}
