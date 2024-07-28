package org.example.battle;

import org.example.enemy.model.AbstractEnemy;
import org.example.enemy.model.EnemyStorage;
import org.example.player.Player;
import org.example.player.dto.Attack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ProcessBattle extends JPanel implements ActionListener {
    private final Timer timer;
    private final Player player;
    private final JButton restartButton;
    private final EnemyStorage enemyChooser = new EnemyStorage();
    private final DrawBattleDesc gameDesc = new DrawBattleDesc();
    private final LevelUpChoosingDesc levelUpChoosingDesc = new LevelUpChoosingDesc();
    private final EndGameDesc endGameDesc = new EndGameDesc();
    private AbstractEnemy enemy;
    private boolean gameOver = false;
    private boolean playerTurn = new Random().nextBoolean();
    private final JTextArea logArea;

    public ProcessBattle() {
        setLayout(null);
        timer = new Timer(BattleSpeed.FAST.getTurnSpeed(), this);
        player = new Player();
        enemy = enemyChooser.getRandomLvlOneEnemy();
        timer.start();

        restartButton = new JButton("Next battle");
        restartButton.setBounds(350, 500, 100, 30);
        restartButton.addActionListener(e -> resetGame());
        add(restartButton);
        restartButton.setVisible(false);

        // Инициализация панели для журнала
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(200, 150, 400, 200); // Центрируем панель в окне
        add(scrollPane);
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
                Attack attack = enemy.attack(player);
                appendLog("%s наносит %s урона".formatted(enemy.getName(), attack.getDamage()));
            } else {
                Attack attack = player.attack(enemy);
                if (attack.isCritical()) {
                    appendLog("%s наносит критичкский урон %s!".formatted(player.getName(), attack.getDamage()));
                } else {
                    appendLog("%s наносит %s урона".formatted(player.getName(), attack.getDamage()));
                }
            }
            playerTurn = !playerTurn;
            repaint();
            checkBattleIsEnd();
        }
    }

    private void checkBattleIsEnd() {
        if (enemy.getCurrentHealth() <= 0) {
            appendLog("%s повержен! Получено %s опыта\n".formatted(enemy.getName(), enemy.getExperienceValue()));
            gameOver = true;
            timer.stop();
            restartButton.setVisible(true);
            player.addExperience(enemy.getExperienceValue());
            if (player.checkLevelUp()) {
                levelUpChoosingDesc.execute(this);
                appendLog("%s достигает нового уровня %s!\n".formatted(player.getName(), player.getLevel()));
            }
            showResetButton();
        } else if (player.getCurrentHealth() <= 0) {
            gameOver = true;
            timer.stop();
            endGameDesc.execute(this);
        }
    }

    public void resetGame() {
        player.refreshEntity();
        enemy.refreshEntity();
        enemy = enemyChooser.getRandomLvlOneEnemy();
        playerTurn = true;
        gameOver = false;
        restartButton.setVisible(false);
        timer.start();
        repaint();
        clearLog();
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

    private void appendLog(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength()); // Прокручиваем к последнему добавленному сообщению
    }

    private void clearLog() {
        logArea.setText(""); // Очищаем текстовое поле
    }
}
