package org.example.battle;

import org.example.battle.DrawBattleDesc;
import org.example.battle.LevelUpChoosingDesc;
import org.example.enemy.EnemyStorage;
import org.example.enemy.model.lvl1.AbstractEnemy;
import org.example.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessBattle extends JPanel implements ActionListener {
    private final Timer timer;
    private final Player player;
    private final JButton restartButton;
    private final EnemyStorage enemyChooser = new EnemyStorage();
    private final DrawBattleDesc gameDesc = new DrawBattleDesc();
    private final LevelUpChoosingDesc levelUpChoosing = new LevelUpChoosingDesc();
    private AbstractEnemy enemy;
    private boolean gameOver = false;
    private boolean playerTurn = true;

    public ProcessBattle() {
        setLayout(null);
        timer = new Timer(300, this);
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
                    levelUpChoosing.showLevelUpDialog(this);
                } else {
                    endGame();
                }
            }

        }
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
        player.refreshEntity();
        enemy.refreshEntity();
        enemy = enemyChooser.getRandomLvlOneEnemy();
        playerTurn = true;
        gameOver = false;
        restartButton.setVisible(false);
        timer.start();
        repaint();
    }

    public AbstractEnemy getEnemy() {
        return enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public void showResetButton() {
        restartButton.setEnabled(true);
    }
}
