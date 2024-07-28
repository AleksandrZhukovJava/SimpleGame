package org.example.battle;

import org.example.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class LevelUpChoosingDesc {
    private final Set<JButton> buttons = new HashSet<>();
    private Player player;

    public void execute(ProcessBattle processGame) {
        player = processGame.getPlayer();
        JDialog dialog = new JDialog((Frame) null, "Lvl up", true);
        dialog.setLayout(new BorderLayout());

        dialog.add(new JLabel("Lvl up! choose upgrade option%nYou beat: %s".formatted(processGame.getEnemy().getName()),
                        SwingConstants.CENTER),
                BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        fillRandomImprovementButtons(dialog, buttonPanel);

        // Добавляем панель с кнопками в диалоговое окно
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setSize(600, 300);
        dialog.setLocationRelativeTo(processGame);

        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Просто игнорируем попытки закрытия
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

        buttons.stream()
                .limit(player.getLevelUpOptionsAmount())
                .sorted(Comparator.comparing(JButton::getText))
                .forEach(panel::add);
    }

    private JButton getNewLevelUpOptionButton(String message, Consumer<Player> movement,
                                              JDialog dialog) {
        JButton option = new JButton(message);
        option.addActionListener(e -> {
            movement.accept(player);
            dialog.dispose();
        });
        return option;
    }
}
