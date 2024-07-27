package org.example.battle;

import javax.swing.*;
import java.awt.*;

public class EndGameDesc {
    public void execute(ProcessBattle processGame) {
        JDialog dialog = new JDialog((Frame) null, "Game Over", true);
        dialog.setLayout(new BorderLayout());

        // Создаем панель для кнопки
        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Начать заново");
        restartButton.addActionListener(e -> {
            dialog.dispose();
            processGame.getPlayer().backToDefault();
            processGame.resetGame();
        });
        buttonPanel.add(restartButton);

        // Добавляем сообщение и кнопку в диалоговое окно
        dialog.add(new JLabel("Вы проиграли, ваш уровень: %s".formatted(processGame.getPlayer().getLevel()),
                        SwingConstants.CENTER),
                BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(processGame); // Центрируем окно по отношению к основному окну
        dialog.setVisible(true);
    }
}
