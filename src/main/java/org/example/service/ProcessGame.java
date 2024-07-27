package org.example.service;

import org.example.model.enemy.AbstractEnemy;
import org.example.model.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessGame extends JPanel implements ActionListener {
    private final Timer timer;
    private final Player player;
    private final JButton restartButton;
    private final EnemyStorage enemyChooser = new EnemyStorage();
    private final DrawGameDesc gameDesc = new DrawGameDesc();
    private AbstractEnemy enemy;
    private boolean gameOver = false;
    private boolean playerTurn = true;

    public ProcessGame() {
        setLayout(null);
        timer = new Timer(200, this);
        player = new Player();
        enemy = enemyChooser.getRandomLvlOneEnemy();
        timer.start();

        restartButton = new JButton("Next battle");
        restartButton.setBounds(350, 500, 100, 30);
        restartButton.addActionListener(e -> resetGame());
        add(restartButton);
        restartButton.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameDesc.drawDesc(g, player, enemy);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            if (playerTurn) {
                enemy.attack(player);
            } else {
                player.attack(enemy);
            }
            playerTurn = !playerTurn;
            repaint();

            if (player.getCurrentHealth() <= 0 || enemy.getCurrentHealth() <= 0) {
                gameOver = true;
                timer.stop();
                if (enemy.getCurrentHealth() <= 0) {
                    restartButton.setVisible(true);
                    showLevelUpDialog();
                } else {
                    endGame();
                }
            }

        }
    }

    private void showLevelUpDialog() {
        JDialog dialog = new JDialog((Frame) null, "Lvl up! choose option", true);
        dialog.setLayout(new GridLayout(3, 1));
        dialog.add(new JLabel("You beat: " + enemy.getName(), SwingConstants.CENTER));

        JButton option1 = new JButton("Option 1");
        option1.addActionListener(e -> dialog.dispose());
        JButton option2 = new JButton("Option 2");
        option2.addActionListener(e -> dialog.dispose());

        dialog.add(option1);
        dialog.add(option2);

        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);

        // Отключаем возможность закрытия окна крестиком
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // Обработчик события закрытия окна
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Просто игнорируем попытки закрытия
                // Сможете добавить что-то вроде всплывающего окна с предупреждением, если нужно
            }
        });

        dialog.setVisible(true);

        restartButton.setVisible(true); // Показать кнопку после закрытия диалогового окна
    }

    private void endGame() {
        // Создаем модальное диалоговое окно
        JDialog dialog = new JDialog((Frame) null, "Game Over", true);
        dialog.setLayout(new BorderLayout());

        // Создаем панель для кнопки
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Неплохой результат");
        okButton.addActionListener(e -> dialog.dispose()); // Закрываем диалог при нажатии на кнопку
        buttonPanel.add(okButton);

        // Добавляем сообщение и кнопку в диалоговое окно
        dialog.add(new JLabel("Вы проиграли, ваш уровень: %s".formatted(player.getLevel()), SwingConstants.CENTER),
                BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this); // Центрируем окно по отношению к основному окну
        dialog.setVisible(true);

        System.exit(0);
    }

    private void resetGame() {
        player.lvlUp();
        player.refreshEntity();
        enemy.refreshEntity();
        enemy = enemyChooser.getRandomLvlOneEnemy();
        playerTurn = true;
        gameOver = false;
        restartButton.setVisible(false);
        timer.start();
        repaint();
    }
}
