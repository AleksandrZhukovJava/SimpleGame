package org.example.battle;

import javax.swing.*;
import java.awt.*;

public class LevelUpChoosingDesc {
    public void showLevelUpDialog(ProcessBattle processGame) {
        JDialog dialog = new JDialog((Frame) null, "Lvl up! choose option", true);
        dialog.setLayout(new BorderLayout());

        // Добавляем сообщение в центр
        dialog.add(new JLabel("You beat: " + processGame.getEnemy().getName(), SwingConstants.CENTER),
                BorderLayout.CENTER);

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2)); // Размещаем кнопки горизонтально

        // Настраиваем кнопки и их действия
        JButton option1 = new JButton("Upgrade Health");
        option1.addActionListener(e -> {
            handleOption1(processGame);
            dialog.dispose();
        });

        JButton option2 = new JButton("Upgrade Attack");
        option2.addActionListener(e -> {
            handleOption2(processGame);
            dialog.dispose();
        });

        // Добавляем кнопки на панель
        buttonPanel.add(option1);
        buttonPanel.add(option2);

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

        processGame.showResetButton(); // Показываем кнопку после закрытия диалогового окна
    }

    private void handleOption1(ProcessBattle processGame) {
        // Действие для кнопки "Upgrade Health"
        processGame.getPlayer().lvlUpAndIncreaseHealth(10);
        JOptionPane.showMessageDialog(null, "Health Upgraded!");
    }

    private void handleOption2(ProcessBattle processGame) {
        // Действие для кнопки "Upgrade Attack"
        processGame.getPlayer().lvlUpAndIncreaseDamage(5);
        JOptionPane.showMessageDialog(null, "Attack Upgraded!");
    }
}
