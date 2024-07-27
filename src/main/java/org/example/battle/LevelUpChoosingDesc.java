package org.example.battle;

import javax.swing.*;
import java.awt.*;

public class LevelUpChoosingDesc {
    public void execute(ProcessBattle processGame) {
        JDialog dialog = new JDialog((Frame) null, "Lvl up! choose option", true);
        dialog.setLayout(new BorderLayout());

        dialog.add(new JLabel("You beat: " + processGame.getEnemy().getName(), SwingConstants.CENTER),
                BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        // Настраиваем кнопки и их действия
        JButton option1 = new JButton("Upgrade Health");
        option1.addActionListener(e -> {
            processGame.getPlayer().lvlUpAndIncreaseHealth(10);
            dialog.dispose();
        });

        JButton option2 = new JButton("Upgrade Attack");
        option2.addActionListener(e -> {
            processGame.getPlayer().lvlUpAndIncreaseDamage(5);
            dialog.dispose();
        });

        JButton option3 = new JButton("Upgrade critical chance");
        option3.addActionListener(e -> {
            processGame.getPlayer().lvlUpAndIncreaseCriticalChance();
            dialog.dispose();
        });

        // Добавляем кнопки на панель
        buttonPanel.add(option1);
        buttonPanel.add(option2);
        buttonPanel.add(option3);

        // Добавляем панель с кнопками в диалоговое окно
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(processGame);

        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Просто игнорируем попытки закрытия
            }
        });

        dialog.setVisible(true);
    }
}
